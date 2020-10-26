package com.capgemini.censusanalyzer;

import com.capgemini.censusanalyzer.CensusAnalyserException.ExceptionType;

public class CensusAnalyserException extends Exception {enum ExceptionType {
    CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE ,STATE_FILE_PROBLM
}

ExceptionType type;

public CensusAnalyserException(String message, ExceptionType type) {
    super(message);
    this.type = type;
}


}
