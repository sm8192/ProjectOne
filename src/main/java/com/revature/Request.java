package com.revature;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Request {

    @Id
    int id;
    float amount;
    String employee;
    String note;
    String status;

    public Request(){}

    public Request(float amount, String employee, String note){
        this.amount=amount;
        this.employee=employee;
        this.note=note;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public String getEmployee() {
        return employee;
    }

    public String getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                '}';
    }
}
