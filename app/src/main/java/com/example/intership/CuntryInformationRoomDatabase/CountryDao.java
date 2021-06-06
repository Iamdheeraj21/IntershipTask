package com.example.intership.CuntryInformationRoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CountryDao
{
    @Query("SELECT * FROM CountryInformation")
    List<Country> getAll();

    @Insert
    void insertTask(Country country);

    @Delete
    void deleteCountry(Country country);
}
