/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tour.controller;

import lists.TourList;
import lists.TourScheduleList;
import model.tour.TourSchedule;
import util.MyUtil;

/**
 *
 * @author nghialam
 */
public class ControllerTSandT {
    private TourList tourList;
    private TourScheduleList tourScheduleList;
    public ControllerTSandT(){
        tourList = TourList.getInstance();
        tourScheduleList = TourScheduleList.getInstance();
    }

    public TourList getTourList() {
        return tourList;
    }

    public TourScheduleList getTourScheduleList() {
        return tourScheduleList;
    }
    
    public void removeTour(){
        tourList.printListAscendingById();
        String tourID = MyUtil.getString("Enter ID of tour that you want choose: ", "Not space or enter");
        tourList.remove(tourID);
        
        TourSchedule[] tourSchedule = tourScheduleList.getTourScheduleSameTourID(tourID);
        if(tourSchedule == null)
             return;
        for (TourSchedule tourSC1 : tourSchedule) {
            tourScheduleList.remove(tourSC1.getID());
        }
    }
}
