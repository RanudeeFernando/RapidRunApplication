package com.example.rapidrunapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DHDControllerTest {

    @BeforeEach
    public void setUp() {
        AHDController.registeredHorses.clear();
        AHDController.registeredHorses.add(new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null));
        AHDController.registeredHorses.add(new Horse(2, "Horse2", "Jockey2", 6, "Breed2", "Record2", "B", null, null));
    }

    @Test
    public void testDeleteHorseDetails_raceNotStarted() {
        int horseID = 1;    // This is an existing horse ID in the test data
        RACEController.raceStarted = false;

        String result = DHDController.deleteHorseDetails(horseID);

        assertEquals("Horse details deleted successfully!", result);
    }

    @Test
    public void testDeleteHorseDetails_raceStarted() {
        int horseID = 1;    // This is an existing horse ID in the test data
        RACEController.raceStarted = true;

        String result = DHDController.deleteHorseDetails(horseID);

        assertEquals("Sorry, you cannot delete horse details. The race has already started.", result);
    }

    @Test
    public void testDeleteHorseDetails_horseFound() {
        int horseID = 2;    // This is an existing horse ID in the test data
        RACEController.raceStarted = false;

        String result = DHDController.deleteHorseDetails(horseID);

        assertEquals("Horse details deleted successfully!", result);
    }

    @Test
    public void testDeleteHorseDetails_horseNotFound() {
        int horseID = 3; // This ID does not exist in the test data

        String result = DHDController.deleteHorseDetails(horseID);

        assertEquals("Horse not found. Please enter a registered Horse ID.", result);
    }

}