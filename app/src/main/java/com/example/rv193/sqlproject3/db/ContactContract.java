package com.example.rv193.sqlproject3.db;

public class ContactContract {
    public ContactContract() {
        //left blank
    }

    public static class ContactEntry{
        public static final String TABLE_NAME = "contact_info";
        public static final String CONTACT_ID = "contact_id";
        public static final String CONTACT_NAME = "name";
        public static final String CONTACT_EMAIL = "email";
    }
}
