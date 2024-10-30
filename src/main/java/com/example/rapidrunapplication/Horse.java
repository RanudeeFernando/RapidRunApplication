package com.example.rapidrunapplication;

import javafx.scene.image.Image;

public class Horse {
    // Attributes in Horse Class
    private int id;
    private String name;
    private String jockeyName;
    private int age;
    private String breed;
    private String raceRecord;
    private String group;
    private Image thumbnailImage;
    private String imagePath;

    private int raceTime;
    private String place;


    public Horse(int id, String name, String jockeyName, int age, String breed, String raceRecord, String group, Image thumbnailImage, String imagePath) {
        this.id = id;
        this.name = name;
        this.jockeyName = jockeyName;
        this.age = age;
        this.breed = breed;
        this.raceRecord = raceRecord;
        this.group = group;
        this.thumbnailImage = thumbnailImage;
        this.imagePath = imagePath;
    }

    // Getters and setters for Horse attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJockeyName() {
        return jockeyName;
    }

    public void setJockeyName(String jockeyName) {
        this.jockeyName = jockeyName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getRaceRecord() {
        return raceRecord;
    }

    public void setRaceRecord(String raceRecord) {
        this.raceRecord = raceRecord;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getImagePath() {
        return group;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(Image thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public int getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(int raceTime) {
        this.raceTime = raceTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        // Converting Horse details to a string
        return String.format("ID: %d, Horse Name: %s, Jockey Name: %s, Age: %d, Breed: %s, Race Record: %s, Group: %s, Image Path: %s",
                id, name, jockeyName, age, breed, raceRecord, group, imagePath);
    }

}
