/*
 * @(#) PropertiesUtil.java Jul 24, 2017 1:20:48 AM
 *
 * Copyright 2017 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.arh.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil
{
	public static Set<String> getPropertiesFileKey(String path)
	{
		File file = new File(path);
		Properties properties = new Properties();
		if (file.exists() && file.getName().endsWith(".properties"))
		{
			try
			{
				properties.load(new FileInputStream(file));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return properties.stringPropertyNames();

	}
}
