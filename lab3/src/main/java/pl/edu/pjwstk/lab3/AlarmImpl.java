package pl.edu.pjwstk.lab3;

import java.util.ArrayList;
import java.util.List;

public class AlarmImpl implements Alarm {

    private boolean shouldRing;
    private boolean count;
    private TimeImpl time;
    private List<TimeImpl> timeList = new ArrayList<TimeImpl>();

    public boolean shouldRing(){

        String actualTime = time.getTime();
        shouldRing = false;

        for(TimeImpl t : timeList){
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

    public void addAlarmTime(TimeImpl time){
        timeList.add(time);
    }

    public void clearAlarmTime(TimeImpl time){
        timeList.remove(time);
    }

}
