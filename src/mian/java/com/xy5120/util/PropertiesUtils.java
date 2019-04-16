package com.xy5120.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Class Name: PropertiesUtils.java Description: properties工具类
 * 
 * @author xy DateTime 2019年4月16日 上午10:22:45
 * @version 1.0
 */
public class PropertiesUtils {
	// 读取流
	private static FileInputStream fi = null;
	private static FileOutputStream fo = null;

	public static void main(String[] args) {
		String path = "title.properties";
		Properties pro = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File(path));
			pro.load(in);
			int size = pro.size();
			if (size != 0) {
				Set<Object> keySet = pro.keySet();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
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
		JSONObject json=new JSONObject();
		while (it.hasNext()) {
			String next = (String) it.next();
			json.put("index", next);
		}
		
		
		closeAll(fi, fo);
		return null;
	}

	/**
	 * 
	 * Description:关闭读取流
	 * 
	 * @author xy DateTime 2019年4月16日 下午2:28:37
	 * @param in
	 */
	private static void closeAll(FileInputStream fi, FileOutputStream fo) {

		if (fi != null) {
			try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(fo!=null) {
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 *  Description:获取protrties
	 *  @author xy  DateTime 2019年4月16日 下午2:44:11
	 *  @param path
	 *  @return
	 */
	private static Properties getPro(String path) {
		Properties p = new Properties();
		try {
			fi = new FileInputStream(new File(path));
			p.load(fi);
			return p;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return p;
	}
}
