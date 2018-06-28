package com.arh.service;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class DirService
{

	/**
	 * 
	 * @param path
	 *            folder path
	 * @param customFileFilter
	 *            custom file filter
	 * @return File list
	 */
	public List<File> getTargetFiles(String path, FileFilter customFileFilter)
	{
		List<File> list = new ArrayList<File>();
		File dir = new File(path);
		if (dir.exists())
		{
			getTargetFilesRecursion(dir, customFileFilter, list);
		}
		return list;
	}

	private void getTargetFilesRecursion(File target, FileFilter customFileFilter, List<File> result)
	{
		if (target.isDirectory())
		{
			File[] files = target.listFiles(customFileFilter);
			for (File file : files)
			{
				getTargetFilesRecursion(file, customFileFilter, result);
			}
		}
		else
		{
			result.add(target);
		}
	}
}
