package com.xy5120.util;

/**
 * 
 *  Class Name: ChineseNumberUtils.java
 *  Description: 中文数字工具类
 *  @author xy  DateTime 2019年4月14日 上午12:16:23 
 *  @version 1.0
 */
public class ChineseNumberUtils {

	public static void main(String[] args) {
		int number=15101012;
		String chineseNumber = int2ChineseNumber(number);
		System.out.println(chineseNumber);
		System.out.println(chineseNumber2Int(chineseNumber));
		
	}
	/**
	 * 
	 *  Description:数字转中文数字
	 *  @author xy  DateTime 2019年4月14日 上午12:57:54
	 *  @param number
	 *  @return
	 */
	public static String int2ChineseNumber(int number)  {
		final String num[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
	    final String unit[] = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
	    String dst = "";
	    int count = 0;
	    while(number > 0) {
	        dst = (num[number % 10] + unit[count]) + dst;
	        number = number / 10;
	        count++;
	    }
	    return dst.replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
	            .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
	            .replaceAll("零+", "零").replaceAll("零$", "");
	}
	private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                        case 0:
                            temp *= 10;
                            break;
                        case 1:
                            temp *= 100;
                            break;
                        case 2:
                            temp *= 1000;
                            break;
                        case 3:
                            temp *= 10000;
                            break;
                        case 4:
                            temp *= 100000000;
                            break;
                        default:
                            break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }
}
