package observer;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import designchallenge1.Day;
import designchallenge1.Event;

public interface ObserverAdapter {
    public void update(String event, int month, int day, int year, Color color);
}
