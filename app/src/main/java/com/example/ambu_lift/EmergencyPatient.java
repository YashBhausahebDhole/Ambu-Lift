package com.example.ambu_lift;

public class EmergencyPatient {
    public String name;
    public String mbno;
    public String situation;
    public String latitude;
    public String longitude;

    public EmergencyPatient() {

    }

    public EmergencyPatient(String name, String mbno, String situation, String latitude, String longitude) {
        this.name = name;
        this.mbno = mbno;
        this.situation = situation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
