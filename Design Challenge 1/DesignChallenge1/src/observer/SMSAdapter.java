package observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import designchallenge1.Day;
import designchallenge1.Event;
import sms.SMS;
import sms.SMSView;

public class SMSAdapter extends SMSView implements ObserverAdapter{

    @Override
    public void update(String event, int month, int day, int year, Color color){
	Calendar cal = new GregorianCalendar();
	cal.set(GregorianCalendar.YEAR, year);
	cal.set(GregorianCalendar.MONTH, month-1);
	cal.set(GregorianCalendar.DAY_OF_MONTH, day);
	sendSMS(new SMS(event, cal, color));
    }
}
