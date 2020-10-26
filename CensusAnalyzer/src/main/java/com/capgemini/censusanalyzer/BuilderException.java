package com.capgemini.censusanalyzer;

public class BuilderException extends Throwable {

	public enum ExceptionType {
		UNABLE_TO_PARSE, FILE_PROBLEM
	}

	CensusAnalyserException.ExceptionType type;

	public BuilderException(String message, CensusAnalyserException.ExceptionType type) {
		super(message);
		this.type = type;
	}
}
