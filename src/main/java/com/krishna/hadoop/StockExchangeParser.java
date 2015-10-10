package com.krishna.hadoop;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.Text;

public class StockExchangeParser 
{
	  private String dateStr;
	  private long longTime;
	  private String exchangeName;
	  private Double eodIndex;
	  private static final String delimiter= ","; 
	  private static final SimpleDateFormat sdfDDMMYYY =new SimpleDateFormat("dd-MM-yyyy"); 
	  private static final SimpleDateFormat sdfYYYYMMDD =new SimpleDateFormat("yyyy-MM-dd"); 
	 public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public Double getEodIndex() {
		return eodIndex;
	}

	

	public void setEodIndex(Double eodIndex) {
		this.eodIndex = eodIndex;
	}

	
	public long getLongTime() {
		return longTime;
	}

	public void setLongTime(long longTime) {
		this.longTime = longTime;
	}
	
	public void parse(Text recordText)
	  {
		  parse(recordText.toString());
	  }

	private void parse(String recordString) {
		// TODO Auto-generated method stub
		String stockExArray [] = recordString.split(delimiter);
		if(stockExArray.length ==3)
		{
			dateStr =getDateStr(stockExArray[0]);
			longTime = getDateAsTime(dateStr);
			eodIndex =   isNumeric(stockExArray[1]) ? new Double(stockExArray[1]) :0;
			exchangeName=stockExArray[2];
		}
		
	}
	private long getDateAsTime(String dateStr) {
		// TODO Auto-generated method stub
		try {
				return sdfYYYYMMDD.parse(dateStr).getTime();
		}
		 catch(Exception ex)
		{
			 return  0L;
		}
		
	}

	private String getDateStr(String baseDate) {
		// TODO Auto-generated method stub
		try {

			return sdfYYYYMMDD.format((sdfDDMMYYY.parse(baseDate)));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	public  boolean isNumeric(String inputData) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(inputData, pos);
		return inputData.length() == pos.getIndex();
	}
	
	
	public boolean isHeader()
	{
		return (exchangeName.equalsIgnoreCase("STOCK EXCHANGE"));
	}
	
}
