package com.tim.spring0200.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="PHOTO3")
public class PhotoModel {
	private int id;
	private String name;
	private String md5key;
	private int status;
	private String path;
	private String relativePath;
	private String comments;
	private String size;
	
	 @Id 
     @Column(name="id", unique = true, nullable = false)  
     @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "md5key", length = 50)
	public String getMd5key() {
		return md5key;
	}
	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}
	
	@Column(name = "status", length = 2)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "path", length = 200)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "comments", length = 500)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name = "size", length = 10)
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	@Column(name = "relativePath", length = 100)
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PhotoModel)){
			return false;
		}
		PhotoModel p = (PhotoModel)obj;
		if(this.getMd5key() != null && !"".equals(this.getMd5key())){
			if(this.getMd5key().equals(p.getMd5key())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.getName().hashCode();
	}
}
