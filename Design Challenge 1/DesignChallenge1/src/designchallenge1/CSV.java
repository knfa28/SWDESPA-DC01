package designchallenge1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSV implements Parser{

    private CSV csv;
    private ArrayList<Day> days;
	
    public CSV(){
	days = new ArrayList<Day>();
	read();
    }
	
    public void read(){
	Path path = Paths.get("src/designchallenge1/Philippine Holidays.csv");
	Charset cs = StandardCharsets.UTF_8;
	String temp;
		
	try (BufferedReader reader = Files.newBufferedReader(path, cs)){
            while ((temp = reader.readLine()) != null) {
		String[] info = temp.split(", ");
                String[] date = info[0].split("/");
				
                int month = Integer.parseInt(date[0]);
		int day = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		
                Day d = new Day(month, day, year);
		d.addEvent(new Event(info[1], info[2]));
		this.days.add(d);
            }
	}catch(IOException x){
            System.err.println(x);
	}
    }
	
    public ArrayList<Day> getList(){
	return days;
    }
	
    public static void main(String[] args){
	CSV csv = new CSV();
	
        for(int i=0 ;i<csv.getList().size(); i++)
            System.out.println(csv.getList().get(i));
    }
}
