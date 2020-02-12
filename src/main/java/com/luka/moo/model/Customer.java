package com.luka.moo.model;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * Class representing customer
 * It may have name surname, address and id is autogenerated
 */
public class Customer extends DbObject {
    private static int count = 0;

    private String name;
    private String surname;

    private String address;
    private String id;

    public Customer() {
        this.id = generateId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String generateId() {
        return new Date().getTime() + "_" + count++;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("toEmail", surname)
                .add("type", address)
                .toString();
    }

}