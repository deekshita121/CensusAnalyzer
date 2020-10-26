package com.capgemini.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<CSVIndiaCensus> censusCSVIterator = this.getCSVFileIterator(reader, CSVIndiaCensus.class);
			return this.getCount(censusCSVIterator);
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String StateCodeCsvPath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));) {
			Iterator<StateCode> censusCSVIterator = this.getCSVFileIterator(reader, StateCode.class);
			return getCount(censusCSVIterator);
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_FILE_PROBLM);
		}
	}

	private <E> int getCount(final Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		int numOfEntries = (int) StreamSupport.stream(iterable.spliterator(), false).count();
		return numOfEntries;
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}