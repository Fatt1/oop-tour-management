package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity implements Serializable{
	private String activityDecription;
	private String location;
	private LocalDateTime starTime;
	
	
	
	public Activity() {
		this.activityDecription="";
		this.location="";
		this.starTime=LocalDateTime.of(2024,1,1,7,30);
	}
	
	public Activity(String activityDecription , String location , LocalDateTime starTime ) {
		this.activityDecription=activityDecription;
		this.location=location;
		this.starTime=starTime;
	}
	
	public Activity(Activity a1) {
		this.activityDecription=a1.activityDecription;
		this.location=a1.location;
		this.starTime=a1.starTime;
	}
	
	public String getActivityDecription() {
		return activityDecription;
	}
	public void setActivityDecription(String activityDecription) {
		this.activityDecription=activityDecription;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	
	public LocalDateTime getStarTime() {
		return starTime;
	}
	public void setStarTime(LocalDateTime starTime) {
		this.starTime=starTime;
	}
	@Override
	public String toString() {
		return String.format("%-30s|%-10s|%-20s|\n", activityDecription,  location , starTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
	}
	
	public void display() {
		System.out.print(this.toString());
	}
	
}
