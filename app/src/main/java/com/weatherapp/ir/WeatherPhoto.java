package com.weatherapp.ir;

class WeatherPhoto {

    private static String[] cloudy = {
            "Cloudy" ,
            "tornado" ,
            "tropical storm" ,
            "hurricane" ,
            "foggy" ,
            "cloudy" ,
            "mostly cloudy" ,
            "mostly cloudy (day)" ,
            "partly cloudy (night)" ,
            "partly cloudy (day)" ,
            "partly cloudy"
    };

    private static String[] rainy = {
            "severe thunderstorms" ,
            "thunderstorms" ,
            "mixed rain and hail" ,
            "isolated thunderstorms" ,
            "scattered thunderstorms" ,
            "scattered showers" ,
            "thundershowers" ,
            "isolated thundershowers"
    };

    private static String[] sunny = {
            "cold" ,
            "sunny" ,
            "fair (night)" ,
            "clear (night)" ,
            "clear" ,
            "fair (day)" ,
            "hot"
    };

    private static String[] wind = {
            "dust" ,
            "haze" ,
            "smoky" ,
            "windy" ,
            "blustery"
    };

    private static String[] snowy = {
            "mixed rain and snow" ,
            "mixed rain and sleet" ,
            "mixed snow and sleet" ,
            "freezing drizzle" ,
            "freezing rain" ,
            "showers" ,
            "snow flurries" ,
            "light snow showers" ,
            "blowing snow" ,
            "snow" ,
            "hail" ,
            "drizzle" ,
            "sleet" ,
            "heavy snow" ,
            "snow showers"
    };


    static int getPhotoWeather(String text){

        if (isTrue(cloudy , text)){
            return R.drawable.cloudy;
        }
        else if (isTrue(rainy , text)){
            return R.drawable.rainy;
        }
        else if (isTrue(sunny , text)){
            return R.drawable.sunny;
        }
        else if (isTrue(wind , text)){
            return R.drawable.windy;
        }
        else if (isTrue(snowy , text)){
            return R.drawable.snowy;
        }
        else {
            return R.drawable.snowy;
        }
    }


    private static boolean isTrue(String[] list , String text){

        for(String aList : list){
            boolean isTrue = aList.equalsIgnoreCase(text);
            if(isTrue){
                return true;
            }
        }

        return false;
    }
}
