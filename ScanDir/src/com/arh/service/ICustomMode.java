package com.arh.service;

import java.io.File;

import com.arh.pojo.ScanData;

public interface ICustomMode
{

	/**
	 * 
	 * @param targetString
	 *            the string that need to search
	 * @param targetFile
	 *            target File
	 * @param line
	 *            the line that contain the targetString
	 * @param scanData
	 *            used to store result
	 * @return true continue to serach, false otherwise
	 */
	public boolean handleLine(String targetString, File targetFile, String line, ScanData scanData);

	/**
	 * 
	 * @param targetfile
	 *            target File
	 * @param scanData
	 *            used to store result
	 * @return true continue to serach, false otherwise
	 */
	public boolean handleFile(File targetfile, ScanData scanData);
}
