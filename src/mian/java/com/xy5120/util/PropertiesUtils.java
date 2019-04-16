package com.xy5120.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 
 *  Class Name: PropertiesUtils.java
 *  Description: properties工具类
 *  @author xy  DateTime 2019年4月16日 上午10:22:45 
 *  @version 1.0
 */
public class PropertiesUtils {

	public static void main(String[] args)  {
		String path="title.properties";
		Properties pro=new Properties();
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(path));
			pro.load(in);
			int size = pro.size();
			if(size!=0) {
				Set<Object> keySet = pro.keySet();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static String getAll(String path) {
		Properties p = getPro(path);
		Set<Object> keys = p.keySet();
		Iterator<Object> it = keys.iterator();
		while(it.hasNext()) {
			String next = (String)it.next();
		}
		return null;
	}
	private static Properties getPro(String path) {
		Properties p=new Properties();
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(path));
			p.load(in);
			return p;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return p;
	}
}
