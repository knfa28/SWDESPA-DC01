package observer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import designchallenge1.Day;
import designchallenge1.Event;
import facebook.FBView;

public class FBAdapter extends FBView implements ObserverAdapter{

    @Override
    public void update(String event, int month, int day, int year, Color color) {
	showNewEvent(event, month, day, year, color);
    }
}
