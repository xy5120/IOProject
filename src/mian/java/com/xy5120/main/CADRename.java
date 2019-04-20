package com.xy5120.main;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.xy5120.entity.ExcelEntity;
import com.xy5120.util.ExcelUtils;
import com.xy5120.util.FileUtils;

public class CADRename {

	public static Properties p=null;
	public static void main(String[] args) {
		String epath="menu.xls";
		String renamePath="G:\\001华翔宇\\000workspace\\git\\IOProject\\PDF";
		fromExcel2Rename(epath,renamePath);
	}

	/**
	 * 
	 *  Description:
	 *  @author xy  DateTime 2019年4月16日 下午9:48:21
	 *  @param ePath excel的路径
	 *  @param pPath 配置文件的路径
	 */
	public static void fromExcel2Rename(String ePath,String renamePath) {
		Map<String, ExcelEntity> map = ExcelUtils.Excel2Map(ePath, null);
		//保存的文件夹路径
		String newPath=renamePath+File.separator+"重命名文件";
		File newPakage = new File(newPath);
		newPakage.mkdirs();
		
		File file = new File(renamePath);
		//如果不是文件夹就结束程序
		if(!file.isDirectory()) {
			System.out.println("路径不是文件夹");
			System.exit(0);
		}
		File[] listFiles = file.listFiles();
		for (int i=1;i<=map.size();i++) {
			//序号no,图号sheetNo,图名description,图幅sheet
			ExcelEntity excel = map.get(String.valueOf(i));
			String description = excel.getDescription();
			int no = Double.valueOf(excel.getNo()).intValue();
			String sheet = excel.getSheet();
			String sheetNo = excel.getSheetNo();
			//String no = (String)jsonObject.get("no");
			//int no = Integer.parseInt((String)jsonObject.get("no"));
			//String sheet = (String)jsonObject.get("sheet");
			//获取文件格式
			String fileFormat = FileUtils.getFileFormat(listFiles[i-1]);
			//设置文件名--平面图
			String newFileName="装饰Z"+String.format("%03d", no)+"_"+description+"_"+sheet+"."+fileFormat;
			//开始复制
			System.out.println("第"+i+"个，名字为："+newFileName);
			boolean flog = FileUtils.CopyFile(listFiles[i-1], new File(newPath+File.separator+newFileName));
			if(!flog) {
				//出现错误
				System.out.println("错误：第"+i+"个文件重命名失败，文件名是："+listFiles[i-1].getName());
			}
		}
		
		
	}
	
	
	public void rename() {
		//A1-装饰Z100_4#厂房一层原建筑图
				//图号
				String[] pictureNumber= {"A0","A1","A2","A2+1"};
				//开始的序号
				int startNumber=1;
				//int chNum=1;
				//图名
				String[] name= {"原建筑图","平面布置图","机电点位图","隔墙尺寸图","管道井点位图","地坪尺寸图","顶棚布置图","灯具定位图","立面索引图","门表索引图"};
				//要命名的文件夹位置
				String path="C:\\Users\\xy\\Desktop\\2019.03.21沐家护理院施工图最终版PDF";
				//保存的文件夹路径
				String newPath=path+File.separator+"重命名文件";
				File newPakage = new File(newPath);
				newPakage.mkdirs();
				
				File file = new File(path);
				
				//如果不是文件夹就结束程序
				if(!file.isDirectory()) {
					System.out.println("路径不是文件夹");
					System.exit(0);
				}
				File[] listFiles = file.listFiles();
				for (int i=0;i<10;i++) {
					//获取文件格式
					String fileFormat = FileUtils.getFileFormat(listFiles[i]);
					//设置文件名--平面图
					String newFileName=pictureNumber[0]+"-装饰Z"+String.format("%03d", startNumber)+"_4#厂房一层"+name[i]+"."+fileFormat;
					//String newFileName=pictureNumber[0]+"-装饰Z"+String.format("%03d", startNumber)+"_4#厂房一层立面图"+ChineseNumberUtils.int2ChineseNumber(chNum)+"."+fileFormat;
					//System.out.println(newPath+File.separator+newFileName);
					//开始复制
					boolean flog = FileUtils.CopyFile(listFiles[i], new File(newPath+File.separator+newFileName));
					if(flog) {
						//出现错误
						System.out.println("错误：第"+i+"个文件重命名失败，文件名是："+listFiles[i].getName());
					}
					startNumber++;
					//chNum++;
				}
	}
}
