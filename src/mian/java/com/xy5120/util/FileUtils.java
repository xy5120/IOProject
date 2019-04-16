package com.xy5120.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class FileUtils {

	private static final int BYTE_SIZE=1024*1024;
	
	/**
	 * 
	 *  Description:获取文件的格式
	 *  @author xy  DateTime 2019年4月13日 下午7:23:32
	 *  @param file
	 *  @return
	 */
	public static String getFileFormat(File file) {
		String fileName=file.getName();
		int index = fileName.indexOf(".");
		return fileName.substring(index+1);
		
	}
	
	/**
  	 * 
  	 *  Description:拷贝文件
  	 *  @author xy  DateTime 2018-12-6 下午07:28:09
  	 *  @param in
  	 *  @param out
  	 *  @return
  	 *  @throws Exception
  	 */
    public static boolean CopyFile(File in, File out) {
    	try {
			return copy(new FileInputStream(in),new FileOutputStream(out));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
	
	
	
	/**
	 * 
	 *  Description:复制文件抽取
	 *  @author xy  DateTime 2018-12-6 下午10:44:37
	 *  @param input
	 *  @param output
	 *  @return
	 */
	private static boolean copy(InputStream input,OutputStream output){
		BufferedInputStream bfi=null;
		BufferedOutputStream bfo=null;
		try {
			 bfi = new BufferedInputStream(input) ;
			bfo = new BufferedOutputStream(output);
			byte[] b =new  byte[BYTE_SIZE];
			int len=0;
			while((len=bfi.read(b))!=-1){
				bfo.write(b,0, len);
			}
			bfo.flush();
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(bfi!=null){
					bfi.close();
				}
				if(bfo!=null){
					bfo.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
