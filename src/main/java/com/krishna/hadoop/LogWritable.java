package com.krishna.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;



import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
public class LogWritable implements WritableComparable<LogWritable> {
	
	 private Text userIP,request;
	 private LongWritable timestamp;
	 private IntWritable response, status;
    
	  public LogWritable()
	  {
		  userIP =  new Text();
		  request = new Text();
		  timestamp = new LongWritable();
		  response= new IntWritable();
		  status =  new IntWritable();
	  }
	  
	 

	public LogWritable(Text userIP, Text request, LongWritable timestamp, IntWritable response, IntWritable status) {
		super();
		this.userIP = userIP;
		this.request = request;
		this.timestamp = timestamp;
		this.response = response;
		this.status = status;
	}

	 public LogWritable(String userIP_Str,String request_Str,long timestamp_Long, int  response_Int, int status_Int)
	 {
		 userIP = new Text(userIP_Str);
		 request = new Text(request_Str);
		 timestamp = new LongWritable(timestamp_Long);
		 response = new IntWritable(response_Int);
		 status = new IntWritable(status_Int);
	 }

	 
	 
	 

	public Text getUserIP() {
		return userIP;
	}



	public Text getRequest() {
		return request;
	}



	public LongWritable getTimestamp() {
		return timestamp;
	}



	public IntWritable getResponse() {
		return response;
	}



	public IntWritable getStatus() {
		return status;
	}



	public void setUserIP(Text userIP) {
		this.userIP = userIP;
	}



	public void setRequest(Text request) {
		this.request = request;
	}



	public void setTimestamp(LongWritable timestamp) {
		this.timestamp = timestamp;
	}



	public void setResponse(IntWritable response) {
		this.response = response;
	}



	public void setStatus(IntWritable status) {
		this.status = status;
	}



	@Override
	public void readFields(DataInput din) throws IOException {
		// TODO Auto-generated method stub
		userIP.readFields(din);
		request.readFields(din);
		timestamp.readFields(din);
		response.readFields(din);
		status.readFields(din);
	}

	@Override
	public void write(DataOutput dout) throws IOException {
		// TODO Auto-generated method stub
		userIP.write(dout);
		request.write(dout);
		timestamp.write(dout);
		response.write(dout);
		status.write(dout);
	}

	@Override
	public int compareTo(LogWritable that) {
		// TODO Auto-generated method stub
		 int compare = userIP.compareTo(that.request);
		  if (compare != 0)
			  return compare;
		  else
			  return  timestamp.compareTo(that.timestamp);
				
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((userIP == null) ? 0 : userIP.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogWritable other = (LogWritable) obj;
		
		if (userIP == null) {
			if (other.userIP != null)
				return false;
		} else if (!userIP.equals(other.userIP))
			return false;
		
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		
		return true;
	}


	


	@Override
	public String toString() {
		return "LogWritable [userIP=" + userIP + ", request=" + request + ", timestamp=" + timestamp + ", response="
				+ response + ", status=" + status + "]";
	}





	public static class LogWritableComparator extends WritableComparator {
		public LogWritableComparator() {
			super(LogWritable.class);
		}
		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			return compareBytes(b1, s1, l1, b2, s2, l2);
		}
				
	
	}
	
	static { // register this comparator
		WritableComparator.define(LogWritable.class, new LogWritableComparator()) ;
		}
	
	

}
