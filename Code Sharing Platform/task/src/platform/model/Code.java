package platform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "code")
public class Code {

    @Id
    private String id;
    String code;
    String date;
    LocalDateTime dummyDate;
    long time;
    int views;

    long maxTime;
    int maxViews;

    boolean isRestricted;
    boolean isStrictlyRestricted;

    public boolean timeRestriction;
    public boolean viewsRestriction;

    // getters and setters

    @JsonIgnore
    public boolean isTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(boolean timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    @JsonIgnore
    public boolean isViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(boolean viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    @JsonIgnore
    public boolean isStrictlyRestricted() {
        return isStrictlyRestricted;
    }

    public void setStrictlyRestricted(boolean strictlyRestricted) {
        isStrictlyRestricted = strictlyRestricted;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @JsonIgnore
    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    @JsonIgnore
    public int getMaxViews() {
        return maxViews;
    }

    public void setMaxViews(int maxViews) {
        this.maxViews = maxViews;
    }

    @JsonIgnore
    public boolean isRestricted() {
        return isRestricted;
    }

    public void setRestricted(boolean restricted) {
        isRestricted = restricted;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public LocalDateTime getDummyDate() {
        return dummyDate;
    }

    public void setDummyDate(LocalDateTime date) {
        this.dummyDate = date;
    }


    // methods
    @JsonIgnore
    public long getRemainingTime(){
        Duration duration = Duration.between(dummyDate, LocalDateTime.now());
        long seconds = duration.getSeconds();
        if(seconds < 0){
            seconds = -(seconds);
        }
        if(seconds >= maxTime){
            return -1;
        }
        else{
            time = maxTime - seconds;
            return 1;
        }
    }

    @JsonIgnore
    public int getRemainingViews(){
        if(isViewsRestriction()) views = views - 1;
        if(views < 0){
            return -1;
        }
        else{
            return 1;
        }
    }

}