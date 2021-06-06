package com.example.intership;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intership.CuntryInformationRoomDatabase.Country;
import com.example.intership.CuntryInformationRoomDatabase.CountryDatabase;

public class CountryInformation extends AppCompatActivity {

    TextView name,capital,region,subRegion,population;
    ImageView imageView;
    Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_information);
        initViews();
        Country country=(Country) getIntent().getSerializableExtra("country");
        loadInformation(country);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCountryInformation(country);
            }
        });

    }

    private void deleteCountryInformation(Country country)
    {
        class DeleteCountry extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids)
            {
                CountryDatabase.getInstance(getApplicationContext()).getAppDatabase().countryDao().deleteCountry(country);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                finish();
                startActivity(new Intent(CountryInformation.this,MainActivity.class));
            }
        }
        DeleteCountry deleteCountry=new DeleteCountry();
        deleteCountry.execute();
    }

    @SuppressLint("SetTextI18n")
    private void loadInformation(Country country)
    {
        String flagImage=country.getFlag();
        name.setText("Name :-"+country.getName());
        capital.setText("Capital :"+country.getCapital());
        region.setText("Region :-"+country.getRegion());
        subRegion.setText("Sub-Region :-"+country.getSubregion());
        population.setText("Population :-"+country.getPopulation());

        Glide.with(this).load(flagImage).placeholder(R.drawable.ic_round_flag_24).into(imageView);
    }

    private void initViews()
    {
        name=findViewById(R.id.countryName);
        capital=findViewById(R.id.countryCapital);
        deleteButton=findViewById(R.id.deleteCountry);
        region=findViewById(R.id.countryRegion);
        subRegion=findViewById(R.id.countrySubRegion);
        population=findViewById(R.id.countryPopulation);
        imageView=findViewById(R.id.countryFlag);
    }
}