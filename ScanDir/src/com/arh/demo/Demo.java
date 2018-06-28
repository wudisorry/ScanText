/*
 * @(#) Demo.java Jul 24, 2017 1:11:35 AM
 *
 * Copyright 2017 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.arh.demo;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import com.arh.mode.MatchWholeWordMode;
import com.arh.pojo.ScanData;
import com.arh.service.DirService;
import com.arh.service.ScanDirService;

public class Demo
{

	public static void main(String[] args)
	{
		System.out.println(System.currentTimeMillis());
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
		System.out.println(System.currentTimeMillis());
		ScanDirService sds = new ScanDirService();
		ScanData scanData = sds.scanTextFromFiles("SPCAttributeChart.this", files, new MatchWholeWordMode());
		System.out.println(System.currentTimeMillis());
		System.out.println(scanData.getTargetFiles());
	}

}
