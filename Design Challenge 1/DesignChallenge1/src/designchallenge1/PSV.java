package designchallenge1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PSV implements Parser{

    private ArrayList<Day> days;
	
    public PSV(){
        days = new ArrayList<Day>();
        read();
    }
	
    public void read(){
        Path path = Paths.get("src/designchallenge1/DLSU Unicalendar.psv");
        Charset cs = StandardCharsets.UTF_8;
        String temp;
		
        try(BufferedReader reader = Files.newBufferedReader(path, cs)){
            while((temp = reader.readLine()) != null){
                String[] info = temp.split(" \\| ");
                String[] date = info[1].split("/");
                    
                int month = Integer.parseInt(date[0]);
                int day = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                    
                Day d = new Day(month, day, year);
                d.addEvent(new Event(info[0], info[2]));
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
	PSV psv = new PSV();
		
        for(int i=0 ;i<psv.getList().size(); i++)
            System.out.println(psv.getList().get(i));
    }
}
