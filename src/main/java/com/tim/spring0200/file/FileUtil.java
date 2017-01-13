package com.tim.spring0200.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.tim.spring0200.model.PhotoModel;
import com.tim.spring0200.util.MD5Util;

public class FileUtil {
	public static List<PhotoModel> copyFiles(String source,String target) throws IOException{
		File sourceFile = new File(source);
		if(!sourceFile.exists()){
			System.out.println(String.format("source file %s is not exits",source));
			return null;
		}
		File targetFile = new File(target);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		//set Photo properties
		List<PhotoModel> photos = new ArrayList<PhotoModel>();
		
		File[] files = sourceFile.listFiles();
		if(files == null || files.length == 0){
			System.out.print("file directory is not include any file");
			return null;
		}
		int x = 0;
		for(File file:files){
			x ++;
			if(file.isDirectory()){
				photos.addAll(copyFiles(file.getPath(),target + File.separator + file.getName()));
			}
			if(file.isFile()){
				photos.add(copyFile(file,new File(target + File.separator + file.getName())));
			}
		}
		return photos;
	}

	public static PhotoModel copyFile(File source,File target) throws IOException{
		FileInputStream input = new FileInputStream(source);
		FileOutputStream output = new FileOutputStream(target);

		FileChannel in = input.getChannel();  
		FileChannel out = output.getChannel();  
        in.transferTo(0, in.size(), out);  
        
        
        
      //set Photo properties
        String md5key = MD5Util.getMd5ByFile(target);
		String path = target.getPath();
		String unit = "KB";
		float size = (float)target.length() / 1024;
		if (size > 1024) {
			size = size / 1024;
			unit = "MB";
		}
		
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
		String s = df.format(size);
				
		PhotoModel p = new PhotoModel();
		p.setName(target.getName());
		p.setMd5key(md5key);
		p.setStatus(1);
		p.setRelativePath(path);
		p.setPath(path);
		p.setSize(s + unit);
		p.setComments("");
        
        
        
        if(input != null){
        	input.close();
        }
        if(in != null){
        	in.close();
        }
        if(output != null){
        	output.close();
        }
        if(out != null){
        	out.close();
        }
        return p;
	}
	
}
