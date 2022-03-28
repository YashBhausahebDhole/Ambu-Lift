package com.example.ambu_lift;

public class MainPatient {


    public String PatientName;
    public String Pickup;
    public String DropAt;
    public String Date;
    public String Time;
    public String AmbulanceType;


    public MainPatient(String PatientName, String Pickup, String DropAt, String Date, String Time, String AmbulanceType) {
        this.PatientName = PatientName;
        this.Pickup = Pickup;
        this.DropAt = DropAt;
        this.Date = Date;
        this.Time = Time;
        this.AmbulanceType=AmbulanceType;



    }
}
