package com.sample.weatherapp_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.weatherapp_retrofit.Retrofit.ApiClient;
import com.sample.weatherapp_retrofit.Retrofit.ApiInterface;
import com.sample.weatherapp_retrofit.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imageView_search;
    TextView textView_temp, textView_desc, textView_humidity, textView_area;
    EditText editText_searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_searchText = findViewById(R.id.search_text);
        imageView_search = findViewById(R.id.search_icon);

        textView_area = findViewById(R.id.text_area);
        textView_temp = findViewById(R.id.text_temp);
        textView_desc = findViewById(R.id.text_desc);
        textView_humidity = findViewById(R.id.text_humidity);

       // editText_searchText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_area.setText(editText_searchText.getText().toString().trim());
                getWeatherData(editText_searchText.getText().toString().trim());
            }
        });
    }
    private void getWeatherData(String name){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
               // Log.d("Data", response.body().getMain().getTemp());
                textView_temp.setText(response.body().getMain().getTemp()+" °C to °F");
                textView_desc.setText("Feels like"+" "+response.body().getMain().getFeels_like()+" °C");
                textView_humidity.setText("Humidity"+" "+response.body().getMain().getHumidity()+" %");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }
        });

    }
}

