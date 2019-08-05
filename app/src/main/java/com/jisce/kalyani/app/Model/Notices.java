package com.jisce.kalyani.app.Model;

public class Notices {
    String Subject, Dept, Details, Link;

    public Notices(String subject, String dept, String details, String link) {
        Subject = subject;
        Dept = dept;
        Details = details;
        Link = link;
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
}
