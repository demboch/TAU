package pl.edu.pjwstk.lab3;

public interface Alarm {

    boolean shouldRing();
    void addAlarmTime(TimeImpl time);
    void clearAlarmTime(TimeImpl time);
}
