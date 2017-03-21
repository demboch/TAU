package pl.edu.pjwstk.lab3;

import java.util.ArrayList;
import java.util.List;

public class AlarmImpl implements Alarm {

    private List<Time> timeList = new ArrayList<>();
    private boolean shouldRing;
    private boolean count;
    public Time time;

    public boolean shouldRing(){

        String actualTime = time.getTime();
        shouldRing = false;

        for(Time t : timeList){
            if(t.getTime().equals(actualTime)) {
                if(!count) {
                    shouldRing = true;
                    count = true;
                }else{
                    shouldRing = false;
                }
            }else{
                shouldRing = false;
                count = false;
            }
        }
        return shouldRing;
    }

    public void addAlarmTime(Time time){
        timeList.add(time);
    }

    public void clearAlarmTime(Time time){
        timeList.remove(time);
    }

}
