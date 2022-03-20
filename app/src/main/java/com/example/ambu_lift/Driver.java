package com.example.ambu_lift;

public class Driver {
    public String name;
    public String mbno;
    public String mail;
    public String cpass;
    public String ems;
    public  String licence;
    public String owner;
    public String RC;
    public String cwhom;
    public String AmbulanceType;

    public Driver(){

    }

    public Driver(String name, String mbno, String mail, String cpass, String ems,
                  String owner, String licence, String RC,String cwhom,String AmbulanceType ){
        this.name=name;
        this.mbno=mbno;
        this.mail=mail;
        this.cpass=cpass;
        this.ems=ems;
        this.owner=owner;
        this.licence=licence;
        this.RC=RC;
        this.cwhom=cwhom;
        this.AmbulanceType=AmbulanceType;
    }
}
