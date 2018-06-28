package com.arh.mode;

import java.io.File;
import java.util.Set;

import com.arh.pojo.ScanData;
import com.arh.service.ICustomMode;

/**
 * 
 * Contains the specified letter and does not continue to serch
 * <p>
 * 
 */
public class StandMode implements ICustomMode
{

	@Override
	public boolean handleLine(String targetString, File targetFile, String line, ScanData scanData)
	{
		scanData.addTargetFile(targetFile);
		scanData.addFoundNumber();
		return false;
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
