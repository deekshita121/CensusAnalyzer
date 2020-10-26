package com.capgemini.censusanalyzer;

import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class StateCode {

	@CsvBindByName(column = "Serial.No", required =true)
	private int serialNo;

	@CsvBindByName(column = "State Name", required =true)
	private String stateName;

	@CsvBindByName(column = "State Code", required =true)
	private String code;

	@Override
	public String toString() {
		return "States [SerialNo=" + serialNo + ", State Name=" + stateName + ", State Code=" + code + "]";
	}
	

}
