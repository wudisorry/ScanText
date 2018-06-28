package com.arh.mode;

import java.io.File;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.arh.pojo.ScanData;
import com.arh.service.ICustomMode;

public class MatchWholeWordMode implements ICustomMode
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
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean handleFile(File targetfile, ScanData scanData)
	{
		Set<File> files = scanData.getTargetFiles();
		if (files == null || files.size() == 0)
		{
			return true;
		}
		return false;
	}

}
