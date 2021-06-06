package com.example.intership.CuntryInformationRoomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class},exportSchema = false,version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract CountryDao countryDao();
}
