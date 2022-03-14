package com.example.ambu_lift;

public class Paitient {
    public String pname;
    public String pmbno;
    public String pmail;
    public String pcpass;
    public String paddress;
    public String pAge;
    public String pgender;

    public Paitient(){

    }
    public Paitient(String name,String mbno,String mail,String cpass,String address,String Age ,String gender){
        this.pname=name;
        this.pmbno=mbno;
        this.pmail=mail;
        this.pcpass=cpass;
        this.paddress=address;
        this.pAge=Age;
        this.pgender=gender;

    }

}
