package com.example.assign21;

import java.util.ArrayList;

public class Contact {
    private String mName;
    private boolean mOnline;

    public Contact(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact("Person " + ++lastContactId, i <= numContacts / 2));
        }
        return contacts;
    }

    public static ArrayList<Contact> addContact(ArrayList<Contact> contacts) {
        contacts.add(new Contact("Person " + ++lastContactId, lastContactId <= lastContactId/2));
        return contacts;
    }
}
