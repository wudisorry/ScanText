package com.arh.pojo;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ScanData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<File> targetFiles = new HashSet<>();

	private String targetString;

	private int foundNumber;

	private String customString;

	public ScanData()
	{
		super();
	}

	public ScanData(String targetString)
	{
		super();
		this.targetString = targetString;
	}

	public void addTargetFile(File targetFile)
	{
		targetFiles.add(targetFile);
	}

	public void addFoundNumber()
	{
		foundNumber++;
	}

	public Set<File> getTargetFiles()
	{
		return targetFiles;
	}

	public void setTargetFiles(Set<File> targetFiles)
	{
		this.targetFiles = targetFiles;
	}

	public String getTargetString()
	{
		return targetString;
	}

	public void setTargetString(String targetString)
	{
		this.targetString = targetString;
	}

	public int getFoundNumber()
	{
		return foundNumber;
	}

	public void setFoundNumber(int foundNumber)
	{
		this.foundNumber = foundNumber;
	}

	public String getCustomString()
	{
		return customString;
	}

	public void setCustomString(String customString)
	{
		this.customString = customString;
	}

}
