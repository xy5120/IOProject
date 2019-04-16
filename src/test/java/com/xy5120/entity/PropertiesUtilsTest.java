package com.xy5120.entity;

import org.junit.Test;

import com.xy5120.util.PropertiesUtils;

public class PropertiesUtilsTest {

	@Test
	public static void write2PropertiesTest(String ePath,String pPath) {
		PropertiesUtils.write2Properties("key", "我是输入内容", "te.properties");
		
	}
}
