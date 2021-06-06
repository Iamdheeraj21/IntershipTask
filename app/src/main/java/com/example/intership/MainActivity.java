package com.example.intership;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.intership.CuntryInformationRoomDatabase.Country;
import com.example.intership.CuntryInformationRoomDatabase.CountryAdapter;
import com.example.intership.CuntryInformationRoomDatabase.CountryDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        download=findViewById(R.id.download);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,RetriveCountryData.class));
            }
        });
        getAllCountryInformation();
    }

    private void getAllCountryInformation()
    {
        class GetInformation extends AsyncTask<Void,Void, ArrayList<Country>>
        {
            @Override
            protected ArrayList<Country> doInBackground(Void... voids)
            {
                ArrayList<Country> countryList= (ArrayList<Country>) CountryDatabase.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .countryDao()
                        .getAll();
                return countryList;
            }

            @Override
            protected void onPostExecute(ArrayList<Country> countries) {
                super.onPostExecute(countries);
                CountryAdapter countryAdapter=new CountryAdapter(countries,MainActivity.this);
                recyclerView.setAdapter(countryAdapter);
            }
        }

        GetInformation getInformation=new GetInformation();
        getInformation.execute();
    }
}