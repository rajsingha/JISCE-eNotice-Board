package com.jisce.kalyani.app.Model;

public class Notices {
    String Subject, Dept, Details, Link,Date;

    public Notices(){

    }

    public Notices(String subject, String dept, String details, String link,String date) {
        Subject = subject;
        Dept = dept;
        Details = details;
        Link = link;
        Date = date;
    }

    public String getSubject() {
        return Subject;
    }

    public String getDept() {
        return Dept;
    }

    public String getDetails() {
        return Details;
    }

    public String getLink() {
        return Link;
    }
    public String getDate(){
        return Date;
    }
}
