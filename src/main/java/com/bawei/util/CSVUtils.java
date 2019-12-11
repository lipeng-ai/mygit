package com.bawei.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * csv导入导出工具类
 *  <dependency> 
 *   <groupId>org.apache.commons</groupId>
 *   <artifactId>commons-csv</artifactId> 
 *   <version>1.6</version> 
 * </dependency>
 * 
 * @author
 * @date 2018年12月29日 下午3:45:00
 * @desc
 */
public class CSVUtils {
	/**
	 * io流导出
	 * 
	 * @author admin
	 * @date 2019年12月06日 下午3:48:34
	 * @param file
	 *            csv文件(路径+文件名)，csv文件不存在会自动创建
	 * @param dataList
	 *            数据，字符串用逗号分隔
	 * @return 返回导出是否成功 true成功 false失败
	 */
	public static boolean exportCsv(File file, List<String> dataList) {
		boolean isSucess = false;

		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, "GBK");// 解决FileOutputStream中文乱码问题 解决MS office乱码问题
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}

	/**
	 * csv文件导入，返回List集合对象，每个元素是一行字符串，如:aa,bb,cc,dd,ee 
	 * 代码: 
	 * File file=new File("E:/aaa.csv"); 
	 * List<String> list =importCsv(file); 
	 * for(String line:list)
	 * { System.out.println(line); 
	 * }
	 * 
	 * @author
	 * @date 2019年12月06日 下午3:48:34
	 * @param file
	 *            csv文件(路径+文件)
	 * @return 返回List<String>列表
	 */
	public static List<String> importCsv(File file) {
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "GBK");
			br = new BufferedReader(inputStreamReader);
			String line = "";
			while ((line = br.readLine()) != null) {
				// line=new String(line.getBytes("GBK"),"UTF-8");
				dataList.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

	/**
	 * apache commons-csv导出 注意jdk要在1.7及以上使用 map的数据个数要与header的个数相等 并且一一对应，可参照main方法
	 * //io流导出
	 *  File file=new File("E:/aa.csv"); 
	 *  List<String> dataList=new
	 * ArrayList<String>(); 
	 * dataList.add("1,2,3,'/N',4");
	 * dataList.add("1,2,3,'/N',4"); 
	 * exportCsv(file, dataList);
	 * 
	 * // apache commons-csv导出
	 *  String filePath = "E://aaa.csv"; 
	 *  String header1 ="姓名"; 
	 * String header2 = "性别"; 
	 * String header3 = "编号"; 
	 * String header4 = "描述";
	 * 
	 * List<LinkedHashMap<String, String>> recordList = new ArrayList<LinkedHashMap<String, String>>(); 
	 * for (int i = 0; i < 5; i++) {
	 * LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	 * map.put("name", "zhangsan"); 
	 * map.put("sex", "男"); 
	 * map.put("code", "001");
	 * map.put("aa", "aaa"); 
	 * recordList.add(map); 
	 * } 
	 * write(filePath,recordList,header1,header2,header3,header4);
	 * 
	 * @author admin
	 * @date 2019年1月4日 上午10:12:20
	 * @param filePath
	 *            文件存储路径
	 * @param list
	 *            数据列表
	 * @param header
	 *            表头
	 */
	public static void write(String filePath, List<LinkedHashMap<String, String>> list, String... header) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
			CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(header);
			CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
			for (Map<String, String> map : list) {
				csvPrinter.printRecord(map.values());
			}
			csvPrinter.flush();
			csvPrinter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		  File file=new File("E:/book.csv"); 
		  List<String> list =importCsv(file); 
		  for(String line:list)
		  { System.out.println(line); 
		 }
	}
	
	 /**
     * 从字符串中解析出map 可指定分割符
     */
    public static Map<String, String> ParseMap(String str, String item_split, String kv_split) {
        String[] splits = str.split(item_split);
        Map<String, String> map = new HashMap<String, String>();
        for (String item : splits) {
            String[] key_val = item.split(kv_split);
            if (key_val.length == 1) {
                map.put(key_val[0], "");
            } else if (key_val.length >= 2) {
                map.put(key_val[0].trim(), key_val[1].trim());
            }
        }
        return map;
    }
}