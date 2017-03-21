package pl.edu.pjwstk.lab3;

public class TimeImpl {

    private String hour, minute, day, month, year;

    public String getTime(){
        return  (day + month + year + hour + minute);
    }

    public void setTime(String day, String month, String year, String hour, String minute){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }
}
