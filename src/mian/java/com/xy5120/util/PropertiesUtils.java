package com.xy5120.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.alibaba.fastjson.JSON;
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
		//PropertiesUtils.write2Properties("key", "我是输入内容", "te.properties");
		Map<String, JSONObject> all = PropertiesUtils.getAll("test.properties");
		System.out.println(all.size());
		/*String path = "title.properties";
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
		}*/

	}

	/**
	 * 
	 *  Description:将数据写入
	 *  @author xy  DateTime 2019年4月16日 下午9:05:40
	 */
	public static void write2Properties(String key,String value,String pPath) {
		Properties p = getPro(pPath);
		p.put(key, value);
		try {
			p.store(getFO(pPath), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(fi, fo);
		}
		
	}
	private static FileOutputStream getFO(String path) {
		try {
			return new FileOutputStream(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fo;
	}
	public static Map<String, JSONObject> getAll(String path) {
		Map<String,JSONObject> map=new HashMap<String,JSONObject>();
		
		Properties p = getPro(path);
		Set<Object> keys = p.keySet();
		Iterator<Object> it = keys.iterator();
		JSONObject json=new JSONObject();
		while (it.hasNext()) {
			String key = (String) it.next();
			json.put(key, p.get(key));
			map.put(key, JSON.parseObject((String)p.get(key)));
		}
		System.out.println(json);
		
		closeAll(fi, fo);
		return map;
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
			File file = new File(path);
			if(!file.exists()) {
				boolean createNewFile = file.createNewFile();
				System.out.println(path+"。文件不存在，创建文件"+createNewFile);
			}
			fi = new FileInputStream(file);
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
