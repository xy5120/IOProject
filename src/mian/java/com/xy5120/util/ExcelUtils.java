package com.xy5120.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
//我是一个测试
	public static void main(String[] args) {
		String path = "C:\\Users\\Administrator\\Desktop\\目录.xls";
		// get();
		// String value = getVal(path, 1, 3, 3);
		List<ArrayList<String[]>> valLsit = getAllVal(path);
		// sheet
		for (ArrayList<String[]> arrayList : valLsit) {
			// 行
			for (String[] val : arrayList) {
				// 列
				for (int i = 0; i < val.length; i++) {
					if (val[i] == null || "".equals(val[i])) {
						System.out.print("\t");
					}
					System.out.print(val[i] + "--");
				}
				System.out.println();
			}
		}
	}

	/**
	 * 
	 * Description:获取指定表指定单元格的内容
	 * 
	 * @author xy DateTime 2019年3月6日 下午5:33:35
	 * @param path        文件路径
	 * @param sheetIndex  表格
	 * @param rowIndex    行
	 * @param columnIndex 列
	 * @return
	 * @throws IOException
	 */
	public static String getVal(String path, int sheetIndex, int rowIndex, int cellIndex) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet hssfSheet = wb.getSheetAt(sheetIndex - 1);
			if (hssfSheet != null) {
				HSSFRow row = hssfSheet.getRow(rowIndex + 1);
				if (row != null) {
					HSSFCell cell = row.getCell(cellIndex);
					return formatCell(cell, wb);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * Description:获取excel中所有数据
	 * 
	 * @author xy DateTime 2019年3月6日 下午5:08:12
	 * @param path 文件路径
	 * @return List<ArrayList<String[]>> 外层list存储sheet表，里层list存储行数据，String[]存储列内容
	 * @throws IOException
	 */
	public static List<ArrayList<String[]>> getAllVal(String path) {
		// 定义返回容器
		List<ArrayList<String[]>> lists = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> list = null;
		String[] valArray = null;

		try {
			// 创建poi实例
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			// 遍历sheet
			for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
				HSSFSheet hssfSheet = wb.getSheetAt(sheetNum);
				if (hssfSheet == null) {

					continue;
				}
				// 遍历row
				for (int rowNum = 0; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
					// 获取当前行
					HSSFRow row = hssfSheet.getRow(rowNum);
					list = new ArrayList<String[]>();
					if (row == null) {
						continue;
					}
					// 定义数组大小
					valArray = new String[row.getLastCellNum()];
					// 遍历cell
					for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
						String val = formatCell(row.getCell(cellNum), wb);
						System.out.println(val);
						valArray[cellNum] = val;
					}
					list.add(valArray);
				}
				lists.add(list);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lists;

	}

	public static void get() throws IOException {
		String arg0 = "d:\\1.xls";
//		String arg0 = "C:\\1.xls";
		Map<String, String> map = new HashMap<String, String>();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(arg0));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet hssfSheet = wb.getSheetAt(0);// 取得第1页
		int Count = 0;
		if (hssfSheet != null) {
			// System.out.println("最后一行是："+hssfSheet.getLastRowNum());
			// System.out.println("最后一列是："+hssfSheet.getRow(4).getLastCellNum());
			// hssfSheet.getLastRowNum()表示最后一行 代表有多少行
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow row = hssfSheet.getRow(rowNum);
				if (row == null) {
					continue;
				}

				String name = formatCell(row.getCell(0), wb);
				String phone = formatCell(row.getCell(1), wb);
				// map.put(phone, name);
				//System.out.println(("".equals(name) ? "\t" : name) + "___" + ("".equals(phone) ? "\t" : phone));
				Count++;
			}
		}

		System.out.println(Count);
		System.out.println(map.size());

		/*
		 * Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); while
		 * (it.hasNext()) { Map.Entry<String, String> entry = it.next();
		 * System.out.println("姓名： " +entry.getValue() + " ___电话 " + entry.getKey()); }
		 */
	}

	/**
	 * 
	 * Description:返回字符串格式的单元格内容
	 * 
	 * @author xy DateTime 2019年3月6日 下午5:59:23
	 * @param cell 要转换的单元格
	 * @param wb   wb实例，在出现公式型结果时调用
	 * @return
	 */
	private static String formatCell(HSSFCell cell, HSSFWorkbook wb) {
		String val = "";
		if ("".equals(cell) || cell == null) {
			// System.out.println("空");
			return val;
		} else {
			switch (cell.getCellType()) {
			// 字符串类型
			case Cell.CELL_TYPE_STRING:
				val = cell.getStringCellValue();
				break;
			// 数值类型
			case Cell.CELL_TYPE_NUMERIC:
				val = String.valueOf(cell.getNumericCellValue());
				break;
			// 公式型
			case Cell.CELL_TYPE_FORMULA:
				HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(wb);
				val = String.valueOf(evaluator.evaluate(cell).getNumberValue());
				break;
			// 布尔型
			case Cell.CELL_TYPE_BOOLEAN:
				val = String.valueOf(cell.getBooleanCellValue());
				break;
			// 空值
			case Cell.CELL_TYPE_BLANK:
				break;
			// error
			default:
				break;
			}
			return val;

		}
	}

	private static  Workbook	getWorkbook(String path) {
		POIFSFileSystem fs=null;
		Workbook wb=null;
		String extString = path.substring(path.lastIndexOf(".")+1);
		try {
			if("xls".equals(extString.toLowerCase())) {
				fs = new POIFSFileSystem(new FileInputStream(path));
				wb = new HSSFWorkbook(fs);
				
			}else if("xlsx".equals(extString.toLowerCase())) {
				//fs = new POIFSFileSystem(new FileInputStream(path));
				wb = new XSSFWorkbook(new FileInputStream(path));
			}else {
				throw new Exception("格式");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return wb;
	}

	/**
	 * 
	 * Description:写入数据
	 * 
	 * @author xy DateTime 2019年3月6日 下午6:18:10
	 * @param path  写入文件的路径
	 * @param lists 写入文件的lists
	 */
	public static void writeXlsx(String path, List<ArrayList<String[]>> lists) {
		FileOutputStream outputStream = null;
		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			for (int sheetnum = 0; sheetnum < lists.size(); sheetnum++) {
				HSSFSheet sheet = wb.createSheet("" + sheetnum);
				List<String[]> list = lists.get(sheetnum);
				for (int i = 0; i < list.size(); i++) {
					HSSFRow row = sheet.createRow(i);
					String[] str = list.get(i);
					for (int j = 0; j < str.length; j++) {
						HSSFCell cell = row.createCell(j);
						cell.setCellValue(str[j]);
					}
				}
			}
			outputStream = new FileOutputStream(path);
			wb.write(outputStream);

		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}