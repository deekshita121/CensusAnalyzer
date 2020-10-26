package com.capgemini.censusanalyzer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.censusanalyzer.CensusAnalyserException.ExceptionType;
import com.google.gson.Gson;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\IndianStateCensusData-IndianStateCensusData.csv";
	private static final String INCORRECT_INDIA_CENSUS_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\_IndianStateCensusData-IndianStateCensusData.csv";
	private static final String WRONG_CSV_FILE_TYPE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\_IndianStateCensusData-IndianStateCensusData.txt";
	private static final String WRONG_DELIMITER_PATH = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\WrongDelimiter.csv";
	private static final String WRONG_HEADER = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\WrongHeaderIndianStates.csv";
	private static final String STATE_CODE_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\StateCode.csv";
	private static final String INCORRECT_STATE_CODE_CSV_FILE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\_State_Code.csv";
	private static final String WRONG_CSV_FILE_TYPE_STATE_CODE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\State_Code.txt";
	private static final String WRONG_DELIMITER_PATH_STATE_CODE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\WrongDelimiterState.csv";
	private static final String WRONG_HEADER_STATE_CODE = "C:\\Users\\Lenovo\\git\\CensusAnalyzer\\CensusAnalyzer\\src\\test\\resources\\WrongHeaderStateCode.csv";

	@Test
	public void givenTheIndiaCensusCSVFile_WhenRead_NoOfRecordsShouldMatch() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE, CSVBuilderType.OPEN_CSV);
		System.out.println(numOfRecords);
		Assert.assertEquals(6, numOfRecords);
	}

	@Test
	public void givenIncorrectCSVFile_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(INCORRECT_INDIA_CENSUS_CSV_FILE, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);

	}

	@Test
	public void givenIncorrectType_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);
	}

	@Test
	public void givenWrongDelimiter_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_PATH, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);

	}

	@Test
	public void givenWrongHeader_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(WRONG_HEADER, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);

	}

	@Test
	public void givenStateCodeCSVFile_WhenRead_NoOfRecordsShouldMatch() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int numOfRecords = censusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, CSVBuilderType.OPEN_CSV);
		System.out.println(numOfRecords);
		Assert.assertEquals(10, numOfRecords);
	}

	@Test
	public void givenIncorrectStateCodeCSVFile_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadStateCodeData(INCORRECT_STATE_CODE_CSV_FILE, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.STATE_FILE_PROBLM.toString(), exceptionMessage);
	}

	@Test
	public void givenIncorrectTypeStateCode_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadStateCodeData(WRONG_CSV_FILE_TYPE_STATE_CODE, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.CENSUS_FILE_PROBLEM.toString(), exceptionMessage);
	}

	@Test
	public void givenWrongDelimiterOfStateCode_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadStateCodeData(WRONG_DELIMITER_PATH_STATE_CODE, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.STATE_FILE_PROBLM.toString(), exceptionMessage);
	}

	@Test
	public void givenWrongHeaderOfStateCode_ShouldReturnCustomException() throws CensusAnalyserException {
		String exceptionMessage = null;
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadStateCodeData(WRONG_HEADER_STATE_CODE, CSVBuilderType.OPEN_CSV);
		Assert.assertEquals(ExceptionType.STATE_FILE_PROBLM.toString(), exceptionMessage);
	}

	@Test
	public void givenIndianCensus_WhenSortedShouldRetuenSortedResult() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE, CSVBuilderType.OPEN_CSV);
		String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
		CSVIndiaCensus[] censusList = new Gson().fromJson(sortedCensusData, CSVIndiaCensus[].class);
		Assert.assertEquals(censusList[0].state, "Assam");
	}
	
	@Test
	public void givenIndianCensus_WhenSortedShouldReturnSortedResult_StateCode() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE, CSVBuilderType.OPEN_CSV);
		String sortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
		CSVIndiaCensus[] codeList = new Gson().fromJson(sortedCensusData, CSVIndiaCensus[].class);
		Assert.assertEquals(codeList[0].state, "Andhra Pradesh");
	}
	
	@Test
	public void givenIndianCensus_WhenSortedShouldReturn_Population() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE, CSVBuilderType.OPEN_CSV);
		String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
		CSVIndiaCensus[] censusList = new Gson().fromJson(sortedCensusData, CSVIndiaCensus[].class);
		Assert.assertEquals(censusList[0].state, "Uttar Pradesh");
	}
	
	@Test
	public void givenIndianCensus_WhenSortedShouldReturn_Density() throws CensusAnalyserException {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE, CSVBuilderType.OPEN_CSV);
		String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
		CSVIndiaCensus[] censusList = new Gson().fromJson(sortedCensusData, CSVIndiaCensus[].class);
		Assert.assertEquals(censusList[0].state, "Bihar");
	}
}