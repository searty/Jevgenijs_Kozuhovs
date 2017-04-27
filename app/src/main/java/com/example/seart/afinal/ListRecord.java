package com.example.seart.afinal;


import java.io.Serializable;

public class ListRecord implements Serializable {
    public String name;
    public String date;
    public boolean checked = false;


    public ListRecord(String name, String date) {
        setTaskName(name);
        setCreatedDate(date);
    }



    public void setTaskName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setCreatedDate(String date) {
        this.date = date;
    }


    public void setCheck(boolean checked) {this.checked = checked;}

    public String getTaskName() {
        return name;
    }
}
