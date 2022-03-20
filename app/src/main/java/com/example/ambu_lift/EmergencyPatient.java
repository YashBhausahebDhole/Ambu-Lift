package com.example.ambu_lift;

public class EmergencyPatient {
    public String PatientName;
    public String MobileNo;
    public String Situation;
    public String Latitude;
    public String Longitude;


    public EmergencyPatient() {

    }

    public EmergencyPatient(String PatientName, String MobileNo, String Situation, String Latitude, String Longitude) {
        this.PatientName = PatientName;
        this.MobileNo = MobileNo;
        this.Situation = Situation;
        this.Latitude = Latitude;
        this.Longitude = Longitude;


}
}
