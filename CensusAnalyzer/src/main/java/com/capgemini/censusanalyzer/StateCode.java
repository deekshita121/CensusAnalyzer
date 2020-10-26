package com.capgemini.censusanalyzer;

import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class StateCode {

	@CsvBindByName(column = "Serial.No", required =true)
	public int serialNo;

	@CsvBindByName(column = "State Name", required =true)
	public String stateName;

	@CsvBindByName(column = "State Code", required =true)
	public String code;

	@Override
	public String toString() {
		return "States [SerialNo=" + serialNo + ", State Name=" + stateName + ", State Code=" + code + "]";
	}
	

}
