package com.example.ambu_lift;

public class EmergencyPatient {
    public String PatientName;
    public String MobileNo;
    public String Situation;
    public String Latitude;
    public String Longitude;
    public String AmbulanceType;


    public EmergencyPatient() {

    }

    public EmergencyPatient(String PatientName, String MobileNo, String Situation, String Latitude, String Longitude,String AmbulanceType) {
        this.PatientName = PatientName;
        this.MobileNo = MobileNo;
        this.Situation = Situation;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.AmbulanceType=AmbulanceType;


}
}
