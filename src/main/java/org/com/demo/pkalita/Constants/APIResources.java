package org.com.demo.pkalita.Constants;

public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");

    private final String resource;

    // Constructor will load the enum string
    APIResources(String resource) {
        this.resource = resource;
    }

    // method to get the assigned string
    public String getResource(){
        return this.resource;
    }
}
