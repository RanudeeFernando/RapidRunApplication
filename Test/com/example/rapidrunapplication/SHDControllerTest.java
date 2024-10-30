package com.example.rapidrunapplication;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SHDControllerTest {

    @Test
    public void testSortRegisteredHorsesByID_InTheSameGroup() {
        // Creating a list of unsorted horses
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse(2, "Horse2", "Jockey2", 6, "Breed2", "Record2", "A", null, null));
        horses.add(new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null));
        horses.add(new Horse(4, "Horse4", "Jockey4", 8, "Breed4", "Record4", "A", null, null));
        horses.add(new Horse(3, "Horse3", "Jockey3", 7, "Breed3", "Record3", "A", null, null));

        // Sorting the list of horses
        SHDController.sortRegisteredHorses(horses);

        // Creating a list of expected sorted horses
        List<Horse> expectedSortedHorses = new ArrayList<>();
        expectedSortedHorses.add(new Horse(1, "Horse1", "Jockey1", 5, "Breed1", "Record1", "A", null, null));
        expectedSortedHorses.add(new Horse(2, "Horse2", "Jockey2", 6, "Breed2", "Record2", "A", null, null));
        expectedSortedHorses.add(new Horse(3, "Horse3", "Jockey3", 7, "Breed3", "Record3", "A", null, null));
        expectedSortedHorses.add(new Horse(4, "Horse4", "Jockey4", 8, "Breed4", "Record4", "A", null, null));

        // Checking if the horses are sorted correctly
        for (int i = 0; i < horses.size(); i++) {
            assertEquals(expectedSortedHorses.get(i).getId(), horses.get(i).getId());

        }
    }

}