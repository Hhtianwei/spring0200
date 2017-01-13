package com.tim.spring0200.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="WEATHER") 
public class WeatherModel {
	
	private long id;
	private String city;
	private String cityCode;
	private String pm25;
	private String dayStatus;
	private String nightStatus;
	private String qualityStatus;
	private String maxTemperature;
	private String minTemperature;
	private String date;
	private Date createDateTime;
	
	 @Id 
     @Column(name="id", unique = true, nullable = false)  
     @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "city", length = 50)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "cityCode", length = 50)
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Column(name = "pm25", length = 10)
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	
	@Column(name = "dayStatus", length = 50)
	public String getDayStatus() {
		return dayStatus;
	}
	public void setDayStatus(String dayStatus) {
		this.dayStatus = dayStatus;
	}
	
	@Column(name = "nightStatus", length = 50)
	public String getNightStatus() {
		return nightStatus;
	}
	public void setNightStatus(String nightStatus) {
		this.nightStatus = nightStatus;
	}
	
	@Column(name = "qualityStatus", length = 50)
	public String getQualityStatus() {
		return qualityStatus;
	}
	public void setQualityStatus(String qualityStatus) {
		this.qualityStatus = qualityStatus;
	}
	
	@Column(name = "maxTemperature", length = 10)
	public String getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	
	@Column(name = "minTemperature", length = 10)
	public String getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
	}
	
	@Column(name = "date", length = 20)
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name = "createDateTime", length = 20)
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	
}
