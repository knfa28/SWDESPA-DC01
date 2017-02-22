package designchallenge1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import observer.ObserverAdapter;

public class Grabber implements Subject{
	
    private ArrayList<ObserverAdapter> observerAdapters;
    private ArrayList<Day> days;
    private String event;
    private int month;
    private int day;
    private int year;
    private Color color;
	
    public Grabber(ArrayList<Day> days){
	observerAdapters = new ArrayList<ObserverAdapter>();
        this.days = days;
    }
	
    public void notifyNewEvent(String event, int month, int day, int year, String color){
        this.event = event;
        this.month = month;
        this.day = day;
        this.year = year;
                
	if(color.equalsIgnoreCase("White"))
            this.color = Color.white;
        else if(color.equalsIgnoreCase("Red"))
            this.color = Color.red;
        else if(color.equalsIgnoreCase("Green"))
            this.color = Color.green;
        else if(color.equalsIgnoreCase("Blue"))
            this.color = Color.blue;
                
	notifyObservers();
    }
	
    @Override
    public void register(ObserverAdapter oa){
	observerAdapters.add(oa);
		
	Calendar cal = new GregorianCalendar();
	int month = cal.get(GregorianCalendar.MONTH) + 1;
	int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
	int year = cal.get(GregorianCalendar.YEAR);
	
	month = 11;
	day = 1;
	year = 2014;
	
	for(Day d : days)
            if(d.getMonth() == month && d.getDay() == day && d.getYear() == year)
		for(Event event : d.getEvent())
                    oa.update(event.getName(), d.getMonth(), d.getDay(), d.getYear(), event.getColor());		
    }

    @Override
    public void unregister(ObserverAdapter oa){
	observerAdapters.remove(oa);
    }

    @Override
    public void notifyObservers(){
	for(int i = 0; i < observerAdapters.size(); i++)
            observerAdapters.get(i).update(event, month, day, year, color);
    }
}
