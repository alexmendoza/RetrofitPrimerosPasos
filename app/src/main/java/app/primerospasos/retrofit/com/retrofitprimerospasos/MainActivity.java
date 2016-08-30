package app.primerospasos.retrofit.com.retrofitprimerospasos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.primerospasos.retrofit.com.retrofitprimerospasos.API.RestApi;
import app.primerospasos.retrofit.com.retrofitprimerospasos.Model.Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String url = "http://api.openweathermap.org";
    TextView txt_city, txt_status, txt_humidity, txt_pressure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_humidity = (TextView) findViewById(R.id.txt_humidity);
        txt_pressure = (TextView) findViewById(R.id.txt_press);


        getReport();
    }
    void getReport() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestClient service = retrofit.create(RestClient.class);

        Call<Model> call = service.getWheatherReport();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                try {
                    String city = response.body().getName();

                    String status = response.body().getWeather().get(0).getDescription();

                    String humidity = response.body().getMain().getHumidity().toString();

                    String pressure = response.body().getMain().getPressure().toString();

                    txt_city.setText("Ciudad  :  " + city);
                    txt_status.setText("Estado  :  " + status);
                    txt_humidity.setText("Humedad  : " + humidity);
                    txt_pressure.setText("Presión  :  " + pressure);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }





        });
    }
}
