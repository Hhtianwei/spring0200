package com.tim.spring0200;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tim.spring0200.file.FileUtil;
import com.tim.spring0200.model.PhotoModel;
import com.tim.spring0200.model.WeatherModel;
import com.tim.spring0200.page.Pagination;
import com.tim.spring0200.page.SearchResult;
import com.tim.spring0200.service.PhotoService;
import com.tim.spring0200.service.WeatherService;
import com.tim.spring0200.task.TaskExecutorExample;
import com.tim.spring0200.task.TaskSchedulerExample1;

@Controller
public class TestController {

	@Resource(name="taskExecutorExample")
	private TaskExecutorExample taskExecutorExample;
	
	@Resource(name="taskSchedulerExample1")
	private TaskSchedulerExample1 taskSchedulerExample1;
	
	@Resource(name="weatherService")
	private WeatherService weatherService;
	
	@Resource(name="photoService")
	private PhotoService photoService;
	
    @RequestMapping(value = "/hello1" ,method = RequestMethod.GET)
    public String showIndex(Model model,HttpServletRequest request,HttpServletResponse response) {
    	
		final String path = request.getServletContext().getRealPath("/images");
    	List<File> list = readFiles(path);
    	Map<String,String> movs = convertMovInfo(list,path);
    	model.addAttribute("movs",movs);
        return "index";
    }
    
    //从指定目录复制文件到images
    @RequestMapping(value = "/copyImages" ,method = RequestMethod.POST)
    @ResponseBody
    public String showIndex(String filePath,Model model,HttpServletRequest request,HttpServletResponse response) {
		final String path = request.getServletContext().getRealPath("/images");
		List<PhotoModel> list = null;
		try {
			list = FileUtil.copyFiles(filePath, path);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		
		if(list == null || list.size() == 0){
			System.out.println("load image is empty");
			return "empty image";
		}
		
		//re write relate path
		for(PhotoModel photo:list){
			String relatePath = photo.getRelativePath();
			relatePath = relatePath.replace(path, "");
			//relatePath = relatePath.substring(relatePath.indexOf(path), relatePath.length());
			photo.setRelativePath(relatePath);
		}
		photoService.savePhoto(list);
		
        return "finish";
    }
    
    @RequestMapping(value = "/upload" ,method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }
    
    @RequestMapping(value = "/testScheduler" ,method = RequestMethod.GET)
    @ResponseBody
    public String testScheduler() {
    	System.out.println("-----testScheduler---start--");
    	taskSchedulerExample1.printMessage();
    	System.out.println("-----testScheduler---end--");
        return "testScheduler";
    }
    
    
    private Map<String, String> convertMovInfo(List<File> list,String path) {
    	if(list == null || list.isEmpty()){
    		System.out.println("list is empty");
    		return null;
    	}

    	Map<String,String> map = new HashMap<String,String>();
    	
    	for(File file:list){
    		String filePath = file.getPath();
    		String rPath = filePath.substring(path.length()); 
    		map.put(file.getName(), rPath);
    	}
		return map;
	}

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public void uploadFile(MultipartHttpServletRequest request,
			HttpServletResponse response,String str) throws UnsupportedEncodingException {
		String basePath = request.getServletContext().getRealPath("/upload");
		FileOutputStream fileOutputStream = null;
		List<MultipartFile> listUploadFile = request.getFiles("files");
		if(listUploadFile == null || listUploadFile.size() == 0){
			System.out.println("upload file,but file list is empty");
			return ;
		}
		System.out.println("start upload file...");
		long start = System.currentTimeMillis();
		for (int i = 0; i < listUploadFile.size(); i++) {
			MultipartFile file = listUploadFile.get(i);
			
			if (!file.isEmpty()) {
				long startEvery = System.currentTimeMillis();
				String fileName = file.getOriginalFilename();
				System.out.println(String.format("upload file,file name is %s", fileName));
				String name = fileName.split("\\.")[0];
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				//文件名重新命名 oldName + random +  time
				double r = Math.random();
				String newFileName = name + "_" + r + "_" + System.currentTimeMillis()
						+ suffix;
				String filePath = basePath + "\\" + newFileName;
				File files = new File(filePath);
				if(files.exists()){
					files.delete();
				}
				try {
					//files.createNewFile();
					fileOutputStream = new FileOutputStream(files);
					fileOutputStream.write(file.getBytes());
					fileOutputStream.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException ie) {
						ie.printStackTrace();
					}
				}
				long endEvery = System.currentTimeMillis();
				System.out.println(String.format("file %s upload need time :%s" , fileName, msTimeFormat(endEvery-startEvery)));
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("all file needs time is :%s",msTimeFormat(end - start)));
	}
	
	private static String msTimeFormat(long time){
		if(time < 1000){
			return time + "ms";
		}
		double d1 = (double)time / 1000;
		if(d1 < 60){
			return new java.text.DecimalFormat("#.00").format(d1) + " s";
		}
		
		double d2 = d1 / 60;
		if(d2 < 60){
			return new java.text.DecimalFormat("#.00").format(d2) + " mins";
		}
		
		double d3 = d2 /60;
		if(d3 < 24){
			return new java.text.DecimalFormat("#.00").format(d3) + " hours";
		}
		double d4 = d3 / 24;
		return new java.text.DecimalFormat("#.00").format(d4) + " days";
	}


    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@PathVariable String name ) {
    	System.out.println("===3==="+name+"=======");
    	taskExecutorExample.printMessages();
    	System.out.println("===thread finish====");
        return name + ",Hello world";
    }
    
    private List<File> readFiles(String path){
    	File file = new File(path);
    	if(!file.exists()){
    		System.out.println(String.format("file path[%s] is error,can't read file",path));
    		return null;
    	}
    	List<File> list = getFileFromDirectory(file);
    	return list;
    }
    
    private static List<File> getFileFromDirectory(File f){
    	List<File> list = new ArrayList<File>();
    	File[] files = f.listFiles();
    	if(files == null || files.length == 0){
    		return list;
    	}
    	for(File file:files){
    		if(file.isFile()){
    			list.add(file);
    		}else if(file.isDirectory()){
    			list.addAll(getFileFromDirectory(file));
    		}
    	}
    	return list;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String saveWeather(){
    	
    	for(int i=0;i<89;i++){
    		WeatherModel model = new WeatherModel();
    		model.setMaxTemperature("" + i);
    		model.setCity("datong-" + i);
    		model.setCityCode("0352-" + i);
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    		model.setDate(df.format(new Date()));
    		weatherService.saveWeather(model);
    	}
    	
		return "save";
    }
    
    @RequestMapping(value = "/showWeathers", method = RequestMethod.GET)
    public String showWeathers(@RequestParam(value="currentPage", defaultValue="0") int currentPage,Model model){
    	
    	Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(10);
		
		//带条件查询
		Map map = new HashMap<String,Object>();
		map.put("cityCode", "0352-0");
		map.put("city","datong-8");
		
		SearchResult<WeatherModel> searchResult = weatherService.getAllWeatherDataWithCondition(page,map);
    	
		if(searchResult.getResults() == null || searchResult.getResults().size() == 0 ){
			System.out.println("there is no weather data");
		}
		model.addAttribute("searchResult",searchResult);
		return "weatherList";
    }
    
    @RequestMapping(value = "/showPhotos", method = RequestMethod.GET)
    public String showPhotos(@RequestParam(value="currentPage", defaultValue="0") int currentPage,Model model){
    	
    	Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(1);
		
		SearchResult<PhotoModel> searchResult = photoService.getAllPhotoData(page);
    	
		if(searchResult.getResults() == null || searchResult.getResults().size() == 0 ){
			System.out.println("there is no photo data");
		}
		model.addAttribute("searchResult",searchResult);
		return "photoList2";
    }
    
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @ResponseBody
    public String test3( ) {
    	System.out.println("====test3=====");
        return "test3";
    }
    
    @RequestMapping(value = "test4", method = RequestMethod.GET)
    public String test4() {
    	System.out.println("====test4=====");
        return "redirect:" + "/test5";
    }
    
    @RequestMapping(value = "test5", method = RequestMethod.GET)
    @ResponseBody
    public String test5() {
    	System.out.println("====test5=====");
        return "test 5 Hello world";
    }
}
