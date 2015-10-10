package com.krishna.hadoop;

import org.apache.hadoop.io.Text;
/***
 * format of recordLine
 * "0043011990999991950051518004+68750+023550FM-12+038299999V0203201N00261220001CN9999999N9-00111+99999999999"
 * "               Year                                                                    Temp              "
 * @author krishna
 *
 */
public class NCDCParser {
	
	
	private static final int MISSING_TEMPERATURE=9999;
	private String year,airTemperatureStr;
	private int airTemperature;
	private String qualityCode;
	private boolean airTemperatureMalformed;
	private String stationId;
	public void parse(Text record)
	{
		parse(record.toString());
	}

	private void parse(String recordLine) {
		stationId = recordLine.substring(4, 10) + "-" + recordLine.substring(10, 15);
		year =recordLine.substring(15, 19);
		
		if (recordLine.charAt(87) == '+') { 
		      airTemperature = Integer.parseInt(recordLine.substring(88, 92));
		    } else if (recordLine.charAt(87) == '-') {
		      airTemperature = Integer.parseInt(recordLine.substring(87, 92));
		    } else {
		      airTemperatureMalformed = true;
		    }
		qualityCode=recordLine.substring(92, 93);

	}

	public String getStationId() {
	    return stationId;
	  }
	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}

	public String getYear() {
		return year;
	}

	public int getAirTemperature() {
		return airTemperature;
	}

	public boolean isValidTemperature()
	{
		return !airTemperatureMalformed && airTemperature!= MISSING_TEMPERATURE && qualityCode.matches("[01459]");

	}

	 public boolean isMalformedTemperature() {
		    return airTemperatureMalformed;
		  }
	 
	 public boolean isMissingTemperature() {
		    return airTemperature == MISSING_TEMPERATURE;
		  }
}
