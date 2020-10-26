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
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<CSVIndiaCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVIndiaCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVIndiaCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVIndiaCensus> censusCSVIterator = csvToBean.iterator();
			return getCount(censusCSVIterator);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	public int loadStateCodeData(String StateCodeCsvPath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
			CsvToBeanBuilder<StateCode> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(StateCode.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCode> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCode> censusCSVIterator = csvToBean.iterator();
			return getCount(censusCSVIterator);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_FILE_PROBLM);
		}

	}

	private <E> int getCount(final Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int namOfEateries = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return namOfEateries;
    }
}