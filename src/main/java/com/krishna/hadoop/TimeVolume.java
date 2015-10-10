package com.krishna.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class TimeVolume implements WritableComparable<TimeVolume> {

	private long time;
	private double volume;

	
	
	
	public TimeVolume() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeVolume(long time, double volume) {
		
		set( time, volume);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void set(long time, double volume) {
		this.time = time;
		this.volume = volume;

	}

	@Override
	public int compareTo(TimeVolume other) {
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
		this.volume = din.readDouble();
	}

	@Override
	public void write(DataOutput dout) throws IOException {
		// TODO Auto-generated method stub
		dout.writeLong(this.time);
		dout.writeDouble(this.volume);
	}

}
