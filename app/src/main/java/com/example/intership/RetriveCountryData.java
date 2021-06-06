package com.example.intership;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.intership.CuntryInformationRoomDatabase.Country;
import com.example.intership.CuntryInformationRoomDatabase.CountryDatabase;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class RetriveCountryData extends AppCompatActivity
{
    ExtendedFloatingActionButton button;
    RequestQueue newRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_country_data);
        button=findViewById(R.id.extFab);
        newRequestQueue= Volley.newRequestQueue(getApplicationContext());
        button.setOnClickListener(v -> getInformationOfCountry());

    }

    private void getInformationOfCountry()
    {
        String url="https://restcountries.eu/rest/v2/region/asia";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i=0;i<response.length();i++)
            {
                try {
                    JSONObject jsonObject=response.getJSONObject(i);
                    String countryName=jsonObject.getString("name");
                    String countryCapital=jsonObject.getString("capital");
                    String countryRegion=jsonObject.getString("region");
                    String countrySubRegion=jsonObject.getString("subregion");
                    String countryFlag=jsonObject.getString("flag");
                    String countryPopulation=jsonObject.getString("population");

                   /* JSONObject obj1=jsonObject.getJSONObject("borders");
                    String countryBorders=jsonObject.getString(String.valueOf(obj1));

                    JSONObject obj2=jsonObject.getJSONObject("languages");
                    String countryLanguages=jsonObject.getString(String.valueOf(obj2));*/

                    addCountryInfo(countryName,countryCapital,countryRegion,countrySubRegion,countryFlag,countryPopulation);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Toast.makeText(getApplicationContext(), "Fail to get the data..", Toast.LENGTH_SHORT).show());
        newRequestQueue.add(jsonArrayRequest);
    }

    private void addCountryInfo(String countryName, String countryCapital, String countryRegion, String countrySubRegion, String countryFlag, String countryPopulation)
    {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Country info = new Country();

               info.setName(countryName);
               info.setCapital(countryCapital);
               info.setFlag(countryFlag);
               info.setPopulation(countryPopulation);
               info.setRegion(countryRegion);
               info.setSubregion(countrySubRegion);

                //adding to database
                CountryDatabase.getInstance(getApplicationContext()).getAppDatabase()
                        .countryDao()
                        .insertTask(info);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}