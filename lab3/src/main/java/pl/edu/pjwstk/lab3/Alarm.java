package pl.edu.pjwstk.lab3;

public interface Alarm {

    boolean shouldRing();
    void addAlarmTime(Time time);
    void clearAlarmTime(Time time);
}
