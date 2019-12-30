package com.codingelab.Final_Project_409;

public class user_information_External {
    private int Id;
    private String name;
    private String phone;
    private String email;

    public user_information_External() {
    }
    public user_information_External(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public user_information_External(int Id, String name, String phone, String email) {
        this.Id = Id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}


