package com.xy5120.entity;

public class ExcelEntity {

	//序号no,图号sheetNo,图名description,图幅sheet	
	private String no;
	private String SheetNo;
	private String description;
	private String sheet;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getSheetNo() {
		return SheetNo;
	}
	public void setSheetNo(String sheetNo) {
		SheetNo = sheetNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSheet() {
		return sheet;
	}
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}
	public ExcelEntity(String no, String sheetNo, String description, String sheet) {
		super();
		this.no = no;
		SheetNo = sheetNo;
		this.description = description;
		this.sheet = sheet;
	}
	public ExcelEntity() {
	}
	@Override
	public String toString() {
		return "ExcelEntity [no=" + no + ", SheetNo=" + SheetNo + ", description=" + description + ", sheet=" + sheet
				+ "]";
	}
	
	
	
}
