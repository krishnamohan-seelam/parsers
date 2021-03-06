package com.krishna.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TextTime implements WritableComparable<TextTime> {

	private long time;
	private String strVal;

	public static final int SEED = 23;
	
	
	public TextTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TextTime( String strVal ,long time) {
		
		set( strVal, time);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getstrVal() {
		return strVal;
	}

	public void setstrVal(String strVal) {
		this.strVal = strVal;
	}

	public void set(String strVal,long time) {
		this.time = time;
		this.strVal = strVal;

	}

	@Override
	public int compareTo(TextTime other) {
		// TODO Auto-generated method stub
		if(this.strVal.compareTo(other.strVal)!=0)
		{
			return this.strVal.compareTo(other.strVal);
		}

		else if ( this.time != other.time)
		{
			return this.time < other.time ? -1 : 1;
		}
		else
		{
			return 0 ;
		}
			
	}

	@Override
	public void readFields(DataInput din) throws IOException {
		// TODO Auto-generated method stub
		this.time = din.readLong();
		this.strVal = din.readUTF();
	}

	@Override
	public void write(DataOutput dout) throws IOException {
		// TODO Auto-generated method stub
		dout.writeLong(this.time);
		dout.writeUTF(this.strVal);
	}

	public static class TextTimeComparator extends WritableComparator {
		public TextTimeComparator() {
			super(TextTime.class);
		}
		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			return compareBytes(b1, s1, l1, b2, s2, l2);
		}
				
	
	}
	static { // register this comparator
		WritableComparator.define(TextTime.class, new TextTimeComparator()) ;
	}
	
}
