package designchallenge1;

import java.awt.Color;

public class Event{

    private String name;
    private Color color;
    private String colorName;

    public Event(String name, String color){
	this.name = name;
		
        if(color.equalsIgnoreCase("White"))
            this.color = Color.white;
    	else if(color.equalsIgnoreCase("Red"))
            this.color = Color.red;
    	else if(color.equalsIgnoreCase("Green"))
            this.color = Color.green;
    	else if(color.equalsIgnoreCase("Blue"))
            this.color = Color.blue;
	
        setColorName(color);
    }
	
    public String getName(){
	return name;
    }
	
    public Color getColor(){
	return color;
    }
	
    public String toString(){
	return name;
    }

    public String getColorName(){
	return colorName;
    }

    public void setColorName(String colorName){
	this.colorName = colorName;
    }
}
