package com.capgemini.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.CSVBuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {

	List<CSVIndiaCensus> censusCSVList = null;

	public int loadIndiaCensusData(String csvFilePath, CSVBuilderType type) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = (type == CSVBuilderType.OPEN_CSV) ? CSVBuilderFactory.createBuilderOpen()
					: CSVBuilderFactory.createBuilderCommons();
			Iterator<CSVIndiaCensus> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVIndiaCensus.class);
			// csvBuilder.getCSVFileIterator(reader, CSVIndiaCensus.class);
			censusCSVList = csvBuilder.getCSVFileList(reader, CSVIndiaCensus.class);
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
			ICSVBuilder csvBuilder = (type == CSVBuilderType.OPEN_CSV) ? CSVBuilderFactory.createBuilderOpen()
					: CSVBuilderFactory.createBuilderCommons();
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

	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		// TODO Auto-generated method stub
		if (censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("NO_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
		Comparator<CSVIndiaCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusCSVList, censusComparator);
		String toJson = new Gson().toJson(censusCSVList);
		return toJson;
	}

	private void sort(List<CSVIndiaCensus> censusCSVList, Comparator<CSVIndiaCensus> censusComparator) {
		for (int i = 0; i < censusCSVList.size(); i++) {
			for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
				CSVIndiaCensus census1 = censusCSVList.get(j);
				CSVIndiaCensus census2 = censusCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusCSVList.set(j, census2);
					censusCSVList.set(j + 1, census1);
				}

			}

		}
	}

}