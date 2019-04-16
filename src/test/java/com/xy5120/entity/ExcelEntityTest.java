package com.xy5120.entity;


import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class ExcelEntityTest {

	@Test
	public void testExcel() {
		ExcelEntity ee = new ExcelEntity("0","1","1","1");
		JSONObject json=new JSONObject();
		json.put("ee", ee);
		System.out.println("完成json转换");
		System.out.println(json);
		System.out.println(json.toJSONString());
		
	}
	
}
