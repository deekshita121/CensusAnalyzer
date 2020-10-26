package com.capgemini.censusanalyzer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;
import com.capgemini.censusanalyzer.CensusAnalyserException.ExceptionType;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyser\\CensusAnalyser\\src\\test\\resources\\IndianStateCensusData-IndianStateCensusData.csv";
	private static final String INCORRECT_INDIA_CENSUS_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyser\\CensusAnalyser\\src\\test\\resources\\_IndianStateCensusData-IndianStateCensusData.csv";
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
}