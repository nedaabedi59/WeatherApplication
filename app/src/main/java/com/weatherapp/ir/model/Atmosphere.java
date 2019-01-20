
package com.weatherapp.ir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atmosphere {

    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("visibility")
    @Expose
    private float visibility;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("rising")
    @Expose
    private Integer rising;

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
    }

}
