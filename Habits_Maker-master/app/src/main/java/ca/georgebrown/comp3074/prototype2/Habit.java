package ca.georgebrown.comp3074.prototype2;

import java.util.Date;

public class Habit {
    private String name;
    private String type;
    private int day_count;
    private Date lastDate;

    //to let checkBoxDone be use once a day - Alan
    Date lastDateDone;
    Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);

    public Habit(String name, String type, int day_count, Date lastDate) {
        this.name = name;
        this.type = type;
        this.day_count = day_count;
        this.lastDate = lastDate;
        lastDateDone = yesterday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    //to let checkBoxDone be use once a day - Alan
    public Date getLastDateDone() { return lastDateDone; }
    public void setLastDateDone(Date lastDateDone) { this.lastDateDone = lastDateDone; }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
