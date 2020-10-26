package com.capgemini.censusanalyzer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.censusanalyzer.CensusAnalyserException.ExceptionType;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\IndianStateCensusData-IndianStateCensusData.csv";
	private static final String INCORRECT_INDIA_CENSUS_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\_IndianStateCensusData-IndianStateCensusData.csv";
	private static final String WRONG_CSV_FILE_TYPE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\_IndianStateCensusData-IndianStateCensusData.txt";

	@Test
	public void givenTheIndiaCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE);
		System.out.println(numOfRecords);
		Assert.assertEquals(6, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(INCORRECT_INDIA_CENSUS_CSV_FILE);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);

	}
	
	@Test
	public void givenIncorrectType_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);
	}
}