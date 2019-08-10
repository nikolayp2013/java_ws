package ru.javabegin.training.webservices.testws;

import com.cdyne.ws.weatherws.Weather;
import com.cdyne.ws.weatherws.WeatherReturn;
import com.cdyne.ws.weatherws.WeatherSoap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class WeaterWS {

    private String zip;


    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

 
    private Weather weather = new Weather();
    private WeatherSoap port = weather.getWeatherSoap();
    private WeatherReturn weatherReturn;

    public WeatherReturn getWeatherReturn() {
        return weatherReturn;
    }

    public void setWeatherReturn(WeatherReturn weatherReturn) {
        this.weatherReturn = weatherReturn;
    }

    public void findWeather() {
        weatherReturn = port.getCityWeatherByZIP(zip);
  
        
    }
    
    
}
