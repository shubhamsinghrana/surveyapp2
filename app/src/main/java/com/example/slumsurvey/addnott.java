package com.example.slumsurvey;

public class addnott {
    private String slum ,date,slumname;



    public addnott(String a, String b ,String c)
    {
        this.slum=a;
        this.date=b;
        this.slumname=c;




    }

    public String getContent() {
        return slum;
    }

    public void setContent(String content) {
        this.slum = slum;
    }

    public String getDatetime() {
        return date;
    }

    public String getSlumname() {
        return slumname;
    }

    public void setSlumname(String slumname) {
        this.slumname = slumname;
    }

    public void setDatetime(String date) {
        this.date = date;
    }
}
