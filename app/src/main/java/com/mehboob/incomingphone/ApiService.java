package com.mehboob.incomingphone;


import android.view.Display;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

        @POST("/API/login.aspx") // Replace with the actual endpoint path
        Call<Model> performLogin(@Body Model model);


}
