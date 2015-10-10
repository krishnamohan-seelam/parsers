package com.krishna.hadoop;

import org.apache.hadoop.io.Text;

/**
 * USAF   WBAN  STATION NAME                  CTRY ST CALL  LAT     LON      ELEV(M) BEGIN    END
 *"011130 99999 GLOMFJORD                     NO            +66.800 +013.983 +0039.0 20040406 20091216"
 * @author Krishna
 *
 * USAF = Air Force station ID
 * WBAN = NCDC WBAN number
 * CTRY = FIPS country ID
 * ICAO = ICAO ID
 * LON = Longitude in thousandths of decimal degrees
 * LAT = Latitude in thousandths of decimal degrees
 * ELEV = Elevation in meters
 * BEGIN = Beginning Period Of Record (YYYYMMDD).
 * END = Ending Period Of Record (YYYYMMDD).
 */
public class NCDCStationParser {

	private String stationId,stationName;

	public String getStationId() {
		return stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public boolean parse(Text record)
	{
		return parse(record.toString());
	}

	public  boolean parse(String recordLine) {


		if (recordLine.length() < 42)
		{
			return false;
		}
		
		String usaf =recordLine.substring(0,6);
		String wban =recordLine.substring(7,12);
		stationId = usaf +"-"+wban ;
		
		stationName =recordLine.substring(13, 42).trim();
		try {
		      Integer.parseInt(usaf); // USAF identifiers are numeric
		      return true;
		    } catch (NumberFormatException e) {
		      return false;
		    }

	}

}
