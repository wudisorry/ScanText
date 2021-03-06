/*
 * @(#) Demo2.java Jul 24, 2017 1:19:41 AM
 *
 * Copyright 2017 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.arh.demo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.arh.mode.MatchWholeWordMode;
import com.arh.pojo.ScanData;
import com.arh.service.DirService;
import com.arh.service.ScanDirService;
import com.arh.util.PropertiesUtil;

public class Demo2
{

	public static void main(String[] args)
	{
		// System.out.println(System.currentTimeMillis());
		DirService dirService = new DirService();
		List<File> files = dirService.getTargetFiles("E:\\2017MF\\QualityManagement\\code", new FileFilter()
		{

			@Override
			public boolean accept(File pathname)
			{
				if (pathname.isDirectory() || pathname.getName().endsWith(".java"))
				{
					return true;
				}
				return false;
			}
		});
		// System.out.println(System.currentTimeMillis());
		ScanDirService sds = new ScanDirService();
		Set<String> keys = PropertiesUtil.getPropertiesFileKey(
			"E:\\2017MF\\QualityManagement\\code\\QualityManagementWeb\\src\\app_localize\\quality\\QMLang_en_US - backup.properties");
		List<String> targetStrings = new ArrayList<>();
		for (String string : keys)
		{
			targetStrings.add(string);
		}

		List<ScanData> l = new ArrayList<>();

		l = sds.scanTextsFromFiles(targetStrings, files, new MatchWholeWordMode());

		for (ScanData scanData : l)
		{
			System.out.println(scanData.getTargetFiles());
		}
		// System.out.println(System.currentTimeMillis());

	}

}
