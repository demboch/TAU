package pl.edu.pjwstk.lab3;

public class TimeImpl implements Time{

    private String hour, minute, day, month, year;

    @Override
    public String getTime(){
        return  (day + month + year + hour + minute);
    }

    @Override
    public void setTime(String day, String month, String year, String hour, String minute){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }
}
