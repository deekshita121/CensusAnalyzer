package com.capgemini.censusanalyzer;

import com.opencsv.bean.CsvBindByName;

public class StateCode {

	@CsvBindByName(column = "Serial.No")
	private int serialNo;

	@CsvBindByName(column = "State Name")
	private String stateName;

	@CsvBindByName(column = "State Code")
	private String stateCode;

	@Override
	public String toString() {
		return "States [SerialNo=" + serialNo + ", State Name=" + stateName + ", State Code=" + stateCode + "]";
	}
}
