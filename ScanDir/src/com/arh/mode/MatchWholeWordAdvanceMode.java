/*
 * @(#) MatchWholeWordAdvanceMode.java Jul 18, 2017 11:54:34 PM
 *
 * Copyright 2017 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.arh.mode;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.arh.pojo.ScanData;
import com.arh.service.ICustomMode;

public class MatchWholeWordAdvanceMode implements ICustomMode
{

	@Override
	public boolean handleLine(String targetString, File targetFile, String line, ScanData scanData)
	{
		Pattern pattern = Pattern.compile("(?:.*\\W|\\W*)" + targetString + "(?:\\W.*|\\W*)");
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches())
		{
			scanData.addTargetFile(targetFile);
			scanData.addFoundNumber();
		}
		return true;
	}

	@Override
	public boolean handleFile(File targetfile, ScanData scanData)
	{
		return true;
	}

}
