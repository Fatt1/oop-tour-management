package model;

import java.io.Serializable;

public class TourScheduleDetails implements Serializable{
    private String id;
    private int dayNumber;
    private String hotelId;
    private int hotelCost;
    private String restaurantId;
    private int mealCost;
    private int otherCost;
    private int tongTien1Ngay;
    private Activity activity;

    // Constructor không tham số
    public TourScheduleDetails() {
        this.id = "";
        this.dayNumber = 0;
        this.hotelId = "";
        this.hotelCost = 0;
        this.restaurantId = "";
        this.mealCost = 0;
        this.otherCost = 0;
        this.tongTien1Ngay=0;
        this.activity=null;
    }

    // Constructor có tham số
    public TourScheduleDetails(String id, int dayNumber, String hotelId, int hotelCost, String restaurantId, int mealCost, int otherCost, Activity activity) {
        this.id = id;
        this.dayNumber = dayNumber;
        this.hotelId = hotelId;
        this.hotelCost = hotelCost;
        this.restaurantId = restaurantId;
        this.mealCost = mealCost;
        this.otherCost = otherCost;
        this.tongTien1Ngay=hotelCost + mealCost + otherCost;
        this.activity=activity;
        
    }

    // Constructor sao chép
    public TourScheduleDetails(TourScheduleDetails other) {
        this.id = other.id;
        this.dayNumber = other.dayNumber;
        this.hotelId = other.hotelId;
        this.hotelCost = other.hotelCost;
        this.restaurantId = other.restaurantId;
        this.mealCost = other.mealCost;
        this.otherCost = other.otherCost;
        this.tongTien1Ngay=other.tongTien1Ngay;
        this.activity=other.activity;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(int hotelCost) {
        this.hotelCost = hotelCost;
        updateTongTien1Ngay();
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getMealCost() {
        return mealCost;
    }

    public void setMealCost(int mealCost) {
        this.mealCost = mealCost;
        updateTongTien1Ngay();
    }

    public int getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(int otherCost) {
        this.otherCost = otherCost;
        updateTongTien1Ngay();
    }
    public void updateTongTien1Ngay() {
    	this.tongTien1Ngay = this.hotelCost + this.mealCost + this.otherCost;
    }
    
    public Activity getActivity() {
    	return activity;
    }

    @Override
    public String toString() {
        return String.format(
            "|%-6s|%-10s|%-10s|%-15s|%-15s|%-10s|%-10s|%-20s|",
           id, dayNumber, hotelId, hotelCost, restaurantId, mealCost, otherCost, tongTien1Ngay
        );
    }

    
    public void display() {
    	System.out.print(this.toString());
        activity.display();
    }
}
