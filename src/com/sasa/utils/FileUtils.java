package com.sasa.utils;

import java.io.File;
import java.util.Scanner;

/**
 * 文件操作工具类
 * 1.列出指定目录下的所有文件及其子目录文件
 * 文件数量
 * 消耗时间
 * @author sasa
 *
 */
public class FileUtils {
	public static void list() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要查询的文件目录：");
		String directory = scanner.nextLine();
		long startTime = System.currentTimeMillis(); // 开始时间
		long fileCounts = listDirector(new File(directory));
		long endTime = System.currentTimeMillis(); // 执行完的时间
		System.out.println("共消耗了：" + (endTime - startTime) + " ms，共有：" + fileCounts + "个文件！");
	}
	
	public static long listDirector(File file) {
		long fileCounts = 0;
		// 如果目录不为空
		if (!file.exists()) {
			throw new IllegalArgumentException("该目录不存在！");
		}
		// 判断是否是目录
		if (!file.isDirectory()) {
			throw new IllegalArgumentException("该目录不是文件夹！");
		}
		// 如果是目录
		File[] files = file.listFiles(); // 得到它的子目录的抽象
		// 如果files不为空
		if (files != null && files.length > 0) {
			for (File subFile : files) {
				// 如果子目录是文件夹
				if (subFile.isDirectory()) {
					fileCounts += listDirector(subFile);
				} else {
					++fileCounts;
					System.out.println(subFile);
				}
			}
		}
		
		return fileCounts;
	}
}
