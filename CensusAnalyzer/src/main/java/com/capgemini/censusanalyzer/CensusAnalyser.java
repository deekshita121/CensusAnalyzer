package com.capgemini.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.CSVBuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {

	public int loadIndiaCensusData(String csvFilePath, CSVBuilderType type) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = (type==CSVBuilderType.OPEN_CSV)?
					CSVBuilderFactory.createBuilderOpen(): CSVBuilderFactory.createBuilderCommons();
			Iterator<CSVIndiaCensus> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVIndiaCensus.class);
					//csvBuilder.getCSVFileIterator(reader, CSVIndiaCensus.class);
			List<CSVIndiaCensus> censusCSVList = csvBuilder.getCSVFileList(reader, CSVIndiaCensus.class);
			return censusCSVList.size();
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (BuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	public int loadStateCodeData(String StateCodeCsvPath, CSVBuilderType type) throws CensusAnalyserException {

		try (Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));) {
			ICSVBuilder csvBuilder = (type==CSVBuilderType.OPEN_CSV)?CSVBuilderFactory.createBuilderOpen(): CSVBuilderFactory.createBuilderCommons();
            Iterator<StateCode> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateCode.class);
            List<StateCode> censusCSVList = csvBuilder.getCSVFileList(reader, StateCode.class);
			return censusCSVList.size();
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_FILE_PROBLM);
		} catch (BuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	private <E> int getCount(final Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		int numOfEntries = (int) StreamSupport.stream(iterable.spliterator(), false).count();
		return numOfEntries;
	}

}