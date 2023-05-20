package com.mehboob.incomingphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnswerCallBroadcastReceiver extends BroadcastReceiver {
    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {

 mContext=context;

      if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
           Log.d("TAG","Incoming");

            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                   Log.d("TAG",incomingNumber);

                  uploadToServer(incomingNumber);

                }
            },PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    private void uploadToServer(String phoneNumber) {

        SharedPref sharedPref = new SharedPref(mContext);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPref.fetchApi())

                .addConverterFactory(GsonConverterFactory.create(new Gson()))
//                .client(createOkHttpClient())
                .build();

// Create API service instance
        ApiService apiService = retrofit.create(ApiService.class);







        // Create a Login object with the desired values

        Model login = new Model(phoneNumber,Integer.parseInt(sharedPref.fetchRestaurantId()),sharedPref.fetchApi());

// Make the API call
        Call<Model> call = apiService.performLogin(login);
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                if (response.isSuccessful()) {
                    Model loginResponse = response.body();
                    Toast.makeText(mContext, "Uploaded ", Toast.LENGTH_SHORT).show();
                    // Request successful
                } else {
                    // Request failed
                    Toast.makeText(mContext, "Not upload", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            //    Toast.makeText(, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                // Handle the failure
            }
        });

    }

    void showToast(Context context,String message){
        Toast toast=Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}