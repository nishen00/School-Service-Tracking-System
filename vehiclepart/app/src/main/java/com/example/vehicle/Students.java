package com.example.vehicle;

public class Students {

     String userid;
     String address;
    String name;
   String school;
  String presentStatus;
  String inVehicle;

    public String getInVehicle() {
        return inVehicle;
    }

    public void setInVehicle(String inVehicle) {
        this.inVehicle = inVehicle;
    }

    public Students()
    {

    }

    //public Students(String StudentID,String address,String name,String school,String presentStatus){
        //this.StudentID=StudentID;
       // this.address=address;
        //this.name=name;
        //this.school=school;
       // this.presentStatus=presentStatus;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    // }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPresentStatus() {
        return presentStatus;
    }

    public void setPresentStatus(String presentStatus) {
        this.presentStatus = presentStatus;
    }
}
