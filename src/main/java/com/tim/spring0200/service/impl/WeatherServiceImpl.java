package com.tim.spring0200.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tim.spring0200.common.dao.CommonDAO;
import com.tim.spring0200.http.HttpService;
import com.tim.spring0200.model.WeatherModel;
import com.tim.spring0200.page.Pagination;
import com.tim.spring0200.page.SearchResult;
import com.tim.spring0200.service.WeatherService;

public class WeatherServiceImpl implements WeatherService{

	private CommonDAO commonDAO;
	
	@Override
	public String getWatherByAddress(String address) {
		String weatherURL = "http://apis.baidu.com/heweather/weather/free?city=" + address;
		String result = HttpService.get2(weatherURL, "utf-8");
		System.out.println("result:" + result);
		return result;
	}

	@Override
	//@Transactional使用xml方式加了事务
	public void saveWeather(WeatherModel model) {
		commonDAO.saveOrUpdateEntity(model);
	}

	@Override
	public List<WeatherModel> getAllWeatherData() {
		return commonDAO.loadAllEntities(WeatherModel.class);
	}
	
	// 分页 查询所有数据
	@Override
	public SearchResult getAllWeatherData(Pagination page) {
		String sql = "from WeatherModel";
		return commonDAO.search(sql,page);
	}

	// 分页 查询所有数据 带参数
	@Override 
	public <T> SearchResult<T> getAllWeatherDataWithCondition(Pagination page,Map<String,Object> params) {
		String sql = "from WeatherModel where city=:city and cityCode=:cityCode";
		return commonDAO.search(sql,page,params);
	}
	
	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
		
}
