package com.hdfs.olo.olo_web.plugins.common.utils;

public class DayCompare {

	private int year;
    private int month;
    private int day;
    
    public DayCompare(Builder builder)
    {
        this.year=builder.year;
        this.month = builder.month;
        this.day=builder.day;
    }
    public int getYear()
    {
    	return this.year;
    }
    public void setYear(int year)
    {
    	this.year = year;
    }
    public int getMonth()
    {
    	return this.month;
    }
    public void setMonth(int month)
    {
    	this.month = month;
    }
    public int getDay()
    {
    	return this.day;
    }
    public void setDay(int day)
    {
    	this.day = day;
    }
    
    public static class Builder {
		private int year = 0;
		private int month = 0;
		private int day = 0;
		
		public Builder(){}
		public Builder(DayCompare comp){
			this.year = comp.year;
			this.month = comp.month;
			this.day = comp.day;
		}
		
		public Builder year(int year) {
			this.year = year;
			return this;
		}
		public Builder month(int month) {
			this.month = month;
			return this;
		}
		public Builder day(int day) {
			this.day = day;
			return this;
		}
		public DayCompare build()
		{
			return new DayCompare(this);
		}
	}
}
