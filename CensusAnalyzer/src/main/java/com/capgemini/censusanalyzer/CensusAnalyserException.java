package com.capgemini.censusanalyzer;

import com.capgemini.censusanalyzer.CensusAnalyserException.ExceptionType;

public class CensusAnalyserException extends Exception {
	enum ExceptionType {
		CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, STATE_FILE_PROBLM, FILE_PROBLEM
	}

	ExceptionType type;

	public CensusAnalyserException(String message, String name) {
		super(message);
		this.type = ExceptionType.valueOf(name);
	}

	public CensusAnalyserException(String message, ExceptionType stateFileProblm) {
		// TODO Auto-generated constructor stub

		super(message);
		this.type = type;
	}

}
