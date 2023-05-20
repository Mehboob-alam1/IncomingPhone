package com.mehboob.incomingphone;

import com.google.gson.annotations.SerializedName;

public class Model {
     @SerializedName("phoneNumber")
    private String phoneNumber;
     @SerializedName("restaurantId")
    private int restaurantId;
    @SerializedName("ApiKey")
    private String ApiKey;


    public Model(String phoneNumber, int restaurantId, String apiKey) {
        this.phoneNumber = phoneNumber;
        this.restaurantId = restaurantId;
        ApiKey = apiKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getApiKey() {
        return ApiKey;
    }

    public void setApiKey(String apiKey) {
        ApiKey = apiKey;
    }
}
