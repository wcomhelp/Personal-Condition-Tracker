package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;



public class Condition implements Comparator<Condition>, Comparable<Condition> {

    private String title;
    private Date date;
    private String description;
    private RecordList recordList;
    private ArrayList<String> commentList;
    private static final Integer MAX_CHARACTERS = 100;



    //Start of new code....


    // I needed to explicitly state the default constructor for use elsewhere, and I thought that
    // we should have a constructor that just takes in title, date and description.  I also wanted it
    // for doing some testing in the conditionList class.

   // -DD

    Condition() {}

    Condition(String title, Date date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public int compareTo(Condition condition){
        return this.getDate().compareTo(condition.getDate());
    }


    public int compare(Condition conditionOne, Condition conditionTwo){
        return conditionOne.getDate().compareTo(conditionTwo.getDate());
    }


    //End of new code




    Condition(String title, String description) {

    }


    Condition(String title, Date date, String description, RecordList recordList, ArrayList<String> commentList) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.recordList = recordList;
        this.commentList = commentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {this.title = title; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecordList getRecordList() {
        return recordList;
    }

    public void setRecordList(RecordList recordList) {
        this.recordList = recordList;
    }

    public ArrayList<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<String> commentList) {
        this.commentList = commentList;
    }
}