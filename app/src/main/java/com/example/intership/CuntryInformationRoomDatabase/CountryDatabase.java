package com.example.intership.CuntryInformationRoomDatabase;

import android.content.Context;

import androidx.room.Room;

public class CountryDatabase
{
    private static final String Database_name="My_TodoList";
    private static CountryDatabase instance;
    Context context;

    private final AppDatabase appDatabase;

    private CountryDatabase(Context mCtx) {
        this.context = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, Database_name).build();
    }

    public static synchronized CountryDatabase getInstance(Context mCtx) {
        if (instance == null) {
            instance = new CountryDatabase(mCtx);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
