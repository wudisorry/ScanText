package com.arh.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.arh.pojo.ScanData;

public class ScanDirService
{

	/**
	 * 
	 * @param targetString
	 *            the string that need to search
	 * @param targetFiles
	 *            target file list
	 * @param mode
	 *            custom mode
	 * @return scanData
	 */
	public ScanData scanTextFromFiles(String targetString, List<File> targetFiles, ICustomMode mode)
	{
		ScanData scanData = new ScanData(targetString);
		for (File file : targetFiles)
		{
			scanText(targetString, file, mode, scanData);
			boolean flag = mode.handleFile(file, scanData);
			if (!flag)
			{
				break;
			}
		}
		return scanData;
	}

	private void scanText(String targetString, File targetFile, ICustomMode mode, ScanData scanData)
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "utf-8"));
			String line = br.readLine();
			while (line != null)
			{
				if (line.contains(targetString))
				{
					boolean flag = mode.handleLine(targetString, targetFile, line, scanData);
					if (!flag)
					{
						break;
					}
				}
				line = br.readLine();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param targetStrings
	 *            string list that need to search
	 * @param targetFiles
	 *            target file list
	 * @param mode
	 *            custom mode
	 * @return scanData list
	 */
	public List<ScanData> scanTextsFromFiles(List<String> targetStrings, List<File> targetFiles, ICustomMode mode)
	{
		List<ScanData> result = new ArrayList<>();
		int threadNum = 4;
		int count = targetStrings.size() / 4;
		CountDownLatch latch = new CountDownLatch(4);
		System.out.println("In search process...");
		for (int i = 0; i < threadNum; i++)
		{
			List<String> tempTargetStrings = null;
			if (i == threadNum - 1)
			{
				tempTargetStrings = targetStrings.subList(count * i, targetStrings.size());
			}
			else
			{
				tempTargetStrings = targetStrings.subList(count * i, count * (i + 1));
			}
			CustomThread customThread = new CustomThread(tempTargetStrings, targetFiles, mode, latch, result);
			Thread thread = new Thread(customThread);
			thread.setName(thread + "-i");
			thread.start();
		}
		try
		{
			latch.await();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Finish search");
		return result;
	}

	class CustomThread extends Thread
	{

		private List<File> files;

		private List<String> targetStrings;

		private ICustomMode mode;

		private CountDownLatch latch;

		private List<ScanData> result;

		public CustomThread(List<String> targetStrings, List<File> targetFiles, ICustomMode mode, CountDownLatch latch, List<ScanData> result)
		{
			this.targetStrings = targetStrings;
			this.files = targetFiles;
			this.mode = mode;
			this.latch = latch;
			this.result = result;
		}

		@Override
		public void run()
		{
			for (String targetString : targetStrings)
			{
				ScanData scanData = new ScanData(targetString);
				for (File file : files)
				{
					scanText(targetString, file, mode, scanData);
					boolean flag = mode.handleFile(file, scanData);
					if (!flag)
					{
						break;
					}
				}
				result.add(scanData);
			}
			latch.countDown();
		}
	}

}
