package com.matrimony.Bean;

/**
 * Created by Admin on 2/20/2016.
 */
public class BeanRegistration {

    private int regID;
    private String regName;
    private String regAddress;
    private int regCityID;
    private String regEmail;
    private String regDOB;
    private String regMobile;
    private String regGender;
    private int fregID;

    public int getFregID() {
        return fregID;
    }

    public void setFregID(int fregID) {
        this.fregID = fregID;
    }

    public int getRegCityID() {
        return regCityID;
    }

    public void setRegCityID(int regCityID) {
        this.regCityID = regCityID;
    }

    public int getRegID() {
        return regID;
    }

    public void setRegID(int regID) {
        this.regID = regID;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }



    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegDOB() {
        return regDOB;
    }

    public void setRegDOB(String regDOB) {
        this.regDOB = regDOB;
    }

    public String getRegMobile() {
        return regMobile;
    }

    public void setRegMobile(String regMobile) {
        this.regMobile = regMobile;
    }

    public String getRegGender() {
        return regGender;
    }

    public void setRegGender(String regGender) {
        this.regGender = regGender;
    }
}
