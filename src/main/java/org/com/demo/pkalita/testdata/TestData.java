package org.com.demo.pkalita.testdata;

import org.com.demo.pkalita.pojo.AddPlace;
import org.com.demo.pkalita.pojo.Location;

import java.util.Arrays;

public class TestData {

    public AddPlace addPlacePayload(String name, String language, String address,String phoneNumber){
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setName(name);
        addPlace.setPhoneNumber(phoneNumber);
        addPlace.setWebsite("https://rahulshettyacademy.com");
        addPlace.setTypes(Arrays.asList("shop","hotel","building"));
        Location location = new Location();
        location.setLat(-38.36985);
        location.setLng(33.6545654);
        addPlace.setLocation(location);
        return addPlace;
    }

    public String deletePlacePayload(String placeID){
        return
                "{\r\n    \"place_id\":\""+placeID+"\"\r\n}";
    }
}
