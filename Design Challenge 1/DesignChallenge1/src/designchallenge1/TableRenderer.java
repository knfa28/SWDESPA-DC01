package designchallenge1;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer{

    private ArrayList<Day> days;
    private int monthToday;
    private int yearToday;
	
    public TableRenderer(ArrayList<Day> days, int monthToday, int yearToday){
        this.days = days;
        this.monthToday = monthToday;
	this.yearToday = yearToday;
    }
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
        if (column == 0 || column == 6)
            setBackground(new Color(220,220,255));
        else
            setBackground(Color.WHITE);
            
        setForeground(Color.black);
            
        if(table.getValueAt(row, column) != null){
            String info[] = table.getValueAt(row, column).toString().split(" ");
	    String num = null;
            
            if(info[0].startsWith("<html>"))
	        num = info[1];
            else num = info[0];
	            
	    for(Day day : days){
	        if(day.getRow() == row && day.getColumn() == column && day.getMonth() == monthToday+1 && day.getYear() == yearToday){
	            String temp = "<html> " + num + ".</br>";
	            
                    for(Event event : day.getEvent())
	                temp +=  "<font color = \"" + event.getColorName() + "\">" + event.getName() + "<br></br>";
	                	
	            temp += "</html>";
	            table.setValueAt(temp, row, column);
	        }
	    }
        }
            
        setBorder(null);
        return this;  
    }
}
