package app.primerospasos.retrofit.com.retrofitprimerospasos;

import app.primerospasos.retrofit.com.retrofitprimerospasos.Model.Model;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 29/08/2016.
 */
public interface RestClient {

    @GET("/data/2.5/weather?q=santiago&appid=9c7bcd4b31d50aa780c4f5a43e2519c2")
    Call<Model> getWheatherReport();
}
