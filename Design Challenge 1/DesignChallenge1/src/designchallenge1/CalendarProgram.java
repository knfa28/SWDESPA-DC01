package designchallenge1;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import observer.FBAdapter;
import observer.SMSAdapter;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram{
	
    private ArrayList<Day> days;
	
    /**** Day Components ****/
    private int yearBound, monthBound, dayBound, yearToday, monthToday;

    /**** Swing Components ****/
    private JLabel monthLabel, yearLabel;
    private JButton btnPrev, btnNext, btnAddSMS, btnAddFB;
    private JComboBox cmbYear;
    private JFrame frmMain;
    private Container pane;
    private JScrollPane scrollCalendarTable;
    private JPanel calendarPanel;
	
    private Grabber grabber;
        
    /**** Calendar Table Components ***/
    private JTable calendarTable;
    private DefaultTableModel modelCalendarTable;
        
    public void refreshCalendar(int month, int year){
	String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	int nod, som, i, j;
		
	btnPrev.setEnabled(true);
	btnNext.setEnabled(true);
	
        if (month == 0 && year <= yearBound-10)
            btnPrev.setEnabled(false);
	if (month == 11 && year >= yearBound+100)
            btnNext.setEnabled(false);
                
	monthLabel.setText(months[month]);
	monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 360, 50);
                
	cmbYear.setSelectedItem(""+year);
		
	for(i = 0; i < 6; i++)
            for (j = 0; j < 7; j++)
		modelCalendarTable.setValueAt(null, i, j);
		
	GregorianCalendar cal = new GregorianCalendar(year, month, 1);
	nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	som = cal.get(GregorianCalendar.DAY_OF_WEEK);
	
        for (i = 1; i <= nod; i++){
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
			
            modelCalendarTable.setValueAt(i, row, column);
            
            for(Day day : days){	
		if(month == day.getMonth()-1 && i == day.getDay() && year == day.getYear()){
                    day.setRow(row);
                    day.setColumn(column);
                }
            }
	}
		
	calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer(days, monthToday, yearToday));		
    }
        
    public CalendarProgram(){
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}
		
	Parser csv = new CSV();
	Parser psv = new PSV();
	
        days = new ArrayList<Day>();
	days.addAll(csv.getList());
	days.addAll(psv.getList());
		
        grabber = new Grabber(days);
       
	frmMain = new JFrame ("Calendar Application");
        frmMain.setSize(660, 750);
        pane = frmMain.getContentPane();
	pane.setLayout(null);
	frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	monthLabel = new JLabel ("January");
	yearLabel = new JLabel ("Change year:");
	cmbYear = new JComboBox();
	btnPrev = new JButton ("<<");
	btnNext = new JButton (">>");
        btnAddFB = new JButton("Add FB");
	btnAddSMS = new JButton("Add SMS");
        
        modelCalendarTable = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return true;
            }
        };
                
	calendarTable = new JTable(modelCalendarTable);
        calendarTable.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent evt){  
                int col = calendarTable.getSelectedColumn();  
                int row = calendarTable.getSelectedRow();
                        
                if(calendarTable.getValueAt(row, col) == null)
                    JOptionPane.showMessageDialog(null, "Sorry, you cannot add an event there!");
                        
                else{
	            JPanel addEventP= new JPanel();
	            JTextField eventTF = new JTextField(15);
                    String[] colorBoxItems = {"White", "Red", "Green", "Blue"};
	            JComboBox colorBox = new JComboBox (colorBoxItems);
	            addEventP.add(new JLabel("Event: "));
	            addEventP.add(eventTF);
	            addEventP.add(colorBox);
	            
                    int opt = JOptionPane.showConfirmDialog(null, addEventP, "Add Event", JOptionPane.OK_CANCEL_OPTION);
	                        
                    if(opt == JOptionPane.OK_OPTION){
                        String info = calendarTable.getValueAt(row, col).toString().replaceAll("<html> ", "");
	            	String number = null;
	            	int num;
	            	            
                        try{
	            	    num = Integer.parseInt(info.substring(0,2));
	            	} catch(Exception e){
	            	    num = Integer.parseInt(info.substring(0,1));
	            	}
	                
                        int index = -1;
	                
                        for(int i=0; i<days.size(); i++)
	                    if(days.get(i).getMonth() == monthToday+1 && days.get(i).getDay() == num && days.get(i).getYear() == yearToday)
	                        index = i;
	                        		
	                Event event = new Event(eventTF.getText(), colorBox.getSelectedItem().toString());
	                        	
	                if(index == -1){
	                    Day d = new Day(monthToday + 1, num, yearToday);
	                    
                            d.addEvent(event);
	                    d.setRow(row);
	                    d.setColumn(col);
	                    days.add(d);
	                } else days.get(index).getEvent().add(event);
                    }                       	
                }
            }
        });
                
        scrollCalendarTable = new JScrollPane(calendarTable);
	calendarPanel = new JPanel(null);

	calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
	btnPrev.addActionListener(new btnPrev_Action());
	btnNext.addActionListener(new btnNext_Action());
	cmbYear.addActionListener(new cmbYear_Action());
        btnAddFB.addActionListener(new btnAddFB_Action());
	btnAddSMS.addActionListener(new btnAddSMS_Action());	
		
	pane.add(calendarPanel);
	calendarPanel.add(monthLabel);
	calendarPanel.add(yearLabel);
	calendarPanel.add(cmbYear);
	calendarPanel.add(btnPrev);
	calendarPanel.add(btnNext);
	calendarPanel.add(scrollCalendarTable);
	calendarPanel.add(btnAddFB);
	calendarPanel.add(btnAddSMS);
		
        calendarPanel.setBounds(0, 0, 640, 670);
        monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
	yearLabel.setBounds(390, 610, 160, 40);
	cmbYear.setBounds(460, 610, 160, 40);
	btnPrev.setBounds(20, 50, 100, 50);
	btnNext.setBounds(520, 50, 100, 50);
        btnAddFB.setBounds(150, 610, 100 ,25);
	btnAddSMS.setBounds(30, 610, 100, 25);
	scrollCalendarTable.setBounds(20, 100, 600, 500);
                
	frmMain.setResizable(false);
	frmMain.setVisible(true);
		
	GregorianCalendar cal = new GregorianCalendar();
	dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
	monthBound = cal.get(GregorianCalendar.MONTH);
	yearBound = cal.get(GregorianCalendar.YEAR);
	monthToday = monthBound; 
	yearToday = yearBound;
		
	String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
	
        for (int i = 0; i < 7; i++)
            modelCalendarTable.addColumn(headers[i]);
		
	calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

	calendarTable.getTableHeader().setResizingAllowed(false);
	calendarTable.getTableHeader().setReorderingAllowed(false);

        calendarTable.setColumnSelectionAllowed(true);
	calendarTable.setRowSelectionAllowed(true);
	calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	calendarTable.setRowHeight(76);
	modelCalendarTable.setColumnCount(7);
	modelCalendarTable.setRowCount(6);
		
	for (int i = yearBound-100; i <= yearBound+100; i++)
            cmbYear.addItem(String.valueOf(i));
		
            refreshCalendar (monthBound, yearBound); //Refresh calendar
    }
	
    class btnAddFB_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
            grabber.register(new FBAdapter());
        }
    }
	
    class btnAddSMS_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
            grabber.register(new SMSAdapter());
	}
    }
	
    class btnPrev_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
            if(monthToday == 0){
		monthToday = 11;
		yearToday -= 1;
            } else monthToday -= 1;
			
            refreshCalendar(monthToday, yearToday);
        }
    }
	
    class btnNext_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
            if(monthToday == 11){
		monthToday = 0;
		yearToday += 1;
            } else monthToday += 1;
			
            refreshCalendar(monthToday, yearToday);
	}
    }

    class cmbYear_Action implements ActionListener{
	public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
		String b = cmbYear.getSelectedItem().toString();
		yearToday = Integer.parseInt(b);
		refreshCalendar(monthToday, yearToday);
            }
	}
    }
}
