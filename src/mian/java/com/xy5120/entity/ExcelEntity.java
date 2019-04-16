package com.xy5120.entity;

public class ExcelEntity {

	private String number;
	private String title;
	private String pictureNumber;
	
	
	public ExcelEntity() {
		
	}
	public ExcelEntity(String number, String title, String pictureNumber) {
		super();
		this.number =number;
		this.title = title;
		this.pictureNumber = pictureNumber;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPictureNumber() {
		return pictureNumber;
	}
	public void setPictureNumber(String pictureNumber) {
		this.pictureNumber = pictureNumber;
	}
	
	
	
}
