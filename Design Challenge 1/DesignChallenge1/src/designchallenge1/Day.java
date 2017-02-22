package designchallenge1;

import java.awt.Color;
import java.util.ArrayList;

public class Day{

    private ArrayList<Event> events;
    private int month;
    private int day;
    private int year;
    private int row;
    private int column;
	
    public Day(int month, int day, int year){
	events = new ArrayList<Event>();
	this.month = month;
	this.day = day;
	this.year = year;
    }
	
    public void addEvent(Event event){
	this.events.add(event);
    }
	
    public void removeEvent(Event event){
	this.events.add(event);
    }

    public int getMonth(){
	return month;
    }
    
    public int getDay(){
	return day;
    }
    
    public int getYear(){
	return year;
    }
    
    public int getRow(){
	return row;
    }
    
    public int getColumn(){
	return column;
    }
    
    public void setMonth(int month){
	this.month = month;
    }
    
    public void setDay(int day){
	this.day = day;
    }
    
    public void setYear(int year){
	this.year = year;
    }
    
    public void setRow(int row){
	this.row = row;
    }
        
    public void setColumn(int column){
	this.column = column;
    }

    public ArrayList<Event> getEvent(){
	return events;
    }
	
    public String getDate(){
	return month + "/" + day + "/" + year;
    }
    
    public String toString(){
	String string = new String();
	
        string +=month + "/" + day + "/" + year + "\n";
		
        for(int i = 0 ; i < events.size(); i++)
            string += events.get(i) + "\n";
		
	return string;
    }	
}
