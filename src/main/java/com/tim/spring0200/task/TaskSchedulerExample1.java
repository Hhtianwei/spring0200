package com.tim.spring0200.task;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.tim.spring0200.model.WeatherModel;
import com.tim.spring0200.service.WeatherService;

public class TaskSchedulerExample1 {

	private WeatherService weatherService;

	private class MessagePrint1 implements Runnable{

		private String message;
		
		@Override
		public void run() {
			System.out.println("======message is : + " +this.getMessage()+"====");
			
			//String weatherString = weatherService.getWatherByAddress("datong");
			//System.out.println("invoke task query weather , result is : " + weatherString);
			
			WeatherModel model = new WeatherModel();
			model.setCity("datong");
			model.setCityCode("0352");
			model.setDate("2017-01-04 17:59");
			model.setMaxTemperature("18");
			weatherService.saveWeather(model);
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
	}

	private TaskScheduler taskScheduler;
	public void printMessage(){
		MessagePrint1 task = new MessagePrint1();
		task.setMessage("hello , world ");
		taskScheduler.schedule(task, new CronTrigger("0/10 * * * * ?"));
	}
	
	public TaskScheduler getTaskScheduler() {
		return taskScheduler;
	}
	public void setTaskScheduler(TaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
}