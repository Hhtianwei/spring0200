package com.tim.spring0200.service;

import java.util.List;
import java.util.Map;

import com.tim.spring0200.model.WeatherModel;
import com.tim.spring0200.page.Pagination;
import com.tim.spring0200.page.SearchResult;

public interface WeatherService {
	
	String getWatherByAddress(String address);
	
	void saveWeather(WeatherModel model);
	
	List<WeatherModel> getAllWeatherData();

	<T> SearchResult<T> getAllWeatherData(Pagination page);
	
	<T> SearchResult<T> getAllWeatherDataWithCondition(Pagination page,Map<String,Object> params);
}
