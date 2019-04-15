package com.xy5120.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class ExcelToProperties {

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
				for (int i=0;i<size;i++) {
					//System.out.println("key："+keySet[i]+"--value:"+pro.getProperty(keySet[i]));
				}
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
}
