package com.example.rapidrunapplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UHDControllerTest {

    @Test
    public void testUpdateHorseDetails_AllFieldsValid() {
        AHDController.registeredHorses.add(new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null));

        for (Horse horse: AHDController.registeredHorses){

        UHDController controller = new UHDController();
        String result = controller.updateHorseDetails(horse, "Updated Horse Name", "Updated Jockey Name", "6", "Updated Breed", "Updated Race Record");

        // Checking if the result is as expected
        assertEquals("Horse details updated successfully!", result);

        // Checking if the horse details were updated correctly
        assertEquals("Updated Horse Name", horse.getName());
        assertEquals("Updated Jockey Name", horse.getJockeyName());
        assertEquals(6, horse.getAge());
        assertEquals("Updated Breed", horse.getBreed());
        assertEquals("Updated Race Record", horse.getRaceRecord());

        }
    }

    @Test
    public void testUpdateHorseDetails_AllEmptyFields() {
        Horse horse = new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null);

        UHDController controller = new UHDController();
        String result = controller.updateHorseDetails(horse, "", "", "", null, "");

        assertEquals("Horse details were not updated. Please fill in all fields.", result);
        // Checking if the horse details are the same/not updated
        assertEquals("Horse1", horse.getName());
        assertEquals("Jockey1", horse.getJockeyName());
        assertEquals(5, horse.getAge());
        assertEquals("Breed1", horse.getBreed());
        assertEquals("Record1", horse.getRaceRecord());
    }

    @Test
    public void testUpdateHorseDetails_InvalidAgeUpperLimit() {
        Horse horse = new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null);

        UHDController controller = new UHDController();
        String result = controller.updateHorseDetails(horse, "Updated Horse Name", "Updated Jockey Name", "60", "Updated Breed", "Updated Race Record");

        assertEquals("Horse details were not updated. The entered age 60 is incorrect. Please enter a valid age.", result);
        // Checking if the horse details are the same/not updated
        assertEquals("Horse1", horse.getName());
        assertEquals("Jockey1", horse.getJockeyName());
        assertEquals(5, horse.getAge());
        assertEquals("Breed1", horse.getBreed());
        assertEquals("Record1", horse.getRaceRecord());
    }

    @Test
    public void testUpdateHorseDetails_InvalidAgeLowerLimit() {
        Horse horse = new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null);

        UHDController controller = new UHDController();
        String result = controller.updateHorseDetails(horse, "Updated Horse Name", "Updated Jockey Name", "-1", "Updated Breed", "Updated Race Record");

        assertEquals("Horse details were not updated. The entered age -1 is incorrect. Please enter a valid age.", result);
        // Checking if the horse details are the same/not updated
        assertEquals("Horse1", horse.getName());
        assertEquals("Jockey1", horse.getJockeyName());
        assertEquals(5, horse.getAge());
        assertEquals("Breed1", horse.getBreed());
        assertEquals("Record1", horse.getRaceRecord());
    }

    @Test
    public void testUpdateHorseDetails_InvalidAgeNonInteger() {
        Horse horse = new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null);

        UHDController controller = new UHDController();
        String result = controller.updateHorseDetails(horse, "Updated Horse Name", "Updated Jockey Name", "One", "Updated Breed", "Updated Race Record");

        assertEquals("Horse details were not updated. Please enter a valid integer for Age.", result);
        // Checking if the horse details are the same/not updated
        assertEquals("Horse1", horse.getName());
        assertEquals("Jockey1", horse.getJockeyName());
        assertEquals(5, horse.getAge());
        assertEquals("Breed1", horse.getBreed());
        assertEquals("Record1", horse.getRaceRecord());
    }

    @Test
    public void testUpdateHorseDetails_EmptyBreedField_OneEmptyField() {
        Horse horse = new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null);

        UHDController controller = new UHDController();
        String result = controller.updateHorseDetails(horse, "Updated Horse Name", "Updated Jockey Name", "6", null, "Updated Race Record");

        assertEquals("Horse details were not updated. Please fill in all fields.", result);
        // Checking if the horse details are the same/not updated
        assertEquals("Horse1", horse.getName());
        assertEquals("Jockey1", horse.getJockeyName());
        assertEquals(5, horse.getAge());
        assertEquals("Breed1", horse.getBreed());
        assertEquals("Record1", horse.getRaceRecord());
    }
}