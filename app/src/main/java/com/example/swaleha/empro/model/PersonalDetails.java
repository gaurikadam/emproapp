package com.example.swaleha.empro.model;

public class PersonalDetails {

    public String username;
    public String pass;
    public String name;
    public int contact1;
    public int contact2;
    public String qualification;
    public String emailid;
    public String dob;
    public String address;
    public String bankname;
    public String beneficiary;
    public String accno;
    public String ifscno;
    public String branch;
    public String post;
    public int pendingsick;
    public int pendingcasual;
    public int holidays;
    public int leavesused;
    public int daysworked;
    public int daysabsent;
    public int salary;
    public String monthv;

    public PersonalDetails () {
    }

    public PersonalDetails(int leavesused, int holidays, int pendingsick, int pendingcasual, int daysworked, int daysabsent, int salary, String monthv) {
        this.pendingsick = pendingsick;
        this.pendingcasual = pendingcasual;
        this.holidays = holidays;
        this.leavesused = leavesused;
        this.daysworked = daysworked;
        this.daysabsent = daysabsent;
        this.salary = salary;
        this.monthv = monthv;
    }

    public PersonalDetails(String username,String pass,String name,int contact1,int contact2,String qualification,String emailid,
                           String dob,String address,String bankname,String beneficiary,String accno,String ifscno,String branch,
                           String post,int pendingsick,int pendingcasual,int holidays,int leavesused) {

        this.username = username;
        this.pass = pass;
        this.name = name;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.qualification = qualification;
        this.emailid = emailid;
        this.dob = dob;
        this.address = address;
        this.bankname = bankname;
        this.beneficiary = beneficiary;
        this.accno = accno;
        this.ifscno = ifscno;
        this.branch = branch;
        this.post = post;
        this.pendingsick = pendingsick;
        this.pendingcasual = pendingcasual;
        this.holidays = holidays;
        this.leavesused = leavesused;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact1() {
        return contact1;
    }

    public void setContact1(int contact1) {
        this.contact1 = contact1;
    }

    public int getContact2() {
        return contact2;
    }

    public void setContact2(int contact2) {
        this.contact2 = contact2;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getIfscno() {
        return ifscno;
    }

    public void setIfscno(String ifscno) {
        this.ifscno = ifscno;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getPendingsick() {
        return pendingsick;
    }

    public void setPendingsick(int pendingsick) {
        this.pendingsick = pendingsick;
    }

    public int getPendingcasual() {
        return pendingcasual;
    }

    public void setPendingcasual(int pendingcasual) {
        this.pendingcasual = pendingcasual;
    }

    public int getHolidays() {
        return holidays;
    }

    public int getLeavesused() {
        return leavesused;
    }

    public void setHolidays(int holidays) {
        this.holidays = holidays;
    }

    public void setLeavesused(int leavesused) {
        this.leavesused = leavesused;
    }

    public int getDaysworked() {
        return daysworked;
    }

    public int getDaysabsent() {
        return daysabsent;
    }

    public void setDaysworked(int daysworked) {
        this.daysworked = daysworked;
    }

    public void setDaysabsent(int daysabsent) {
        this.daysabsent = daysabsent;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getMonthv() {
        return monthv;
    }

    public void setMonthv(String monthv) {
        this.monthv = monthv;
    }
}
