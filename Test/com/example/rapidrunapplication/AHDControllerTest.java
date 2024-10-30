package com.example.rapidrunapplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AHDControllerTest {

    @Test
    public void testHorseIDFieldEmpty_RegisterHorse() {
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 5;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(null, horseName, jockeyName, age, breed, raceRecord, group, null);

        assertEquals("Please enter a valid integer for Horse ID.", result);

    }

    @Test
    public void testHorseIDNotPositiveInteger_RegisterHorse() {
        int horseId = -1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 5;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, breed, raceRecord, group, null);

        assertEquals("Please enter a positive integer for Horse ID.", result);

    }


    @Test
    public void testHorseNameFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String jockeyName = "Test Jockey";
        int age = 5;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, null, jockeyName, age, breed, raceRecord, group, null);

        assertEquals("Horse Name Field cannot be empty.", result);

    }

    @Test
    public void testJockeyNameFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        int age = 5;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName,null, age, breed, raceRecord, group, null);

        assertEquals("Jockey Name Field cannot be empty.", result);

    }

    @Test
    public void testAgeFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, null, breed, raceRecord, group, null);

        assertEquals("Please enter a valid integer for Age.", result);

    }

    @Test
    public void testAgeBelowLimit_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = -1;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, breed, raceRecord, group, null);

        assertEquals("The entered age -1 is incorrect. Please enter a valid age.", result);

    }

    @Test
    public void testAgeAboveLimit_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 51;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, breed, raceRecord, group, null);

        assertEquals("The entered age 51 is incorrect. Please enter a valid age.", result);

    }

    @Test
    public void testBreedFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 5;
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, null, raceRecord, group, null);

        assertEquals("Breed Field cannot be empty.", result);

    }

    @Test
    public void testRaceRecordFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 5;
        String breed = "Test Breed";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, breed, null, group, null);

        assertEquals("Race Record Field cannot be empty.", result);

    }

    @Test
    public void testGroupFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 5;
        String breed = "Test Breed";
        String raceRecord = "Test Record";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, breed, raceRecord, null, null);

        assertEquals("Group Field cannot be empty.", result);

    }



    @Test
    public void testImageFieldEmpty_RegisterHorse() {
        int horseId = 1;
        String horseName = "Test Horse";
        String jockeyName = "Test Jockey";
        int age = 5;
        String breed = "Test Breed";
        String raceRecord = "Test Record";
        String group = "A";

        String result = AHDController.registerHorseDetails(horseId, horseName, jockeyName, age, breed, raceRecord, group, null);

        assertEquals("Image Field cannot be empty.", result);

    }

}