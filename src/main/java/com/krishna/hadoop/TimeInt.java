package com.krishna.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class TimeInt implements WritableComparable<TimeInt> {

	private long time;
	private int intVal;

	
	
	
	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	public TimeInt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeInt(long time, int intVal) {
		
		set( time, intVal);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	

	public void set(long time, int intVal) {
		this.time = time;
		this.intVal = intVal;

	}

	@Override
	public int compareTo(TimeInt other) {
		// TODO Auto-generated method stub
		if (this.time < other.time) {
			return -1;
		} else if (this.time > other.time) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public void readFields(DataInput din) throws IOException {
		// TODO Auto-generated method stub
		this.time = din.readLong();
		this.intVal = din.readInt();
	}

	@Override
	public void write(DataOutput dout) throws IOException {
		// TODO Auto-generated method stub
		dout.writeLong(this.time);
		dout.writeInt(this.intVal);
	}

}
