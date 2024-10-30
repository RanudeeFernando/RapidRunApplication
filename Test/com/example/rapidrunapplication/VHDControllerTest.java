package com.example.rapidrunapplication;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VHDControllerTest {

    @Test
    public void testSortRegisteredHorsesByID_CommonIDs() {
        // Creating a list of Horses
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse(3, "Horse3", "Jockey3", 5, "Breed3", "Record3", "C", null, null));
        horses.add(new Horse(1, "Horse1", "Jockey1", 3, "Breed1", "Record1", "A", null, null));
        horses.add(new Horse(2, "Horse2", "Jockey2", 4, "Breed2", "Record2", "B", null, null));

        // Setting horses list as the registeredHorses list
        AHDController.registeredHorses = horses;

        // Sorting the registeredHorses list
        VHDController controller = new VHDController();
        controller.sortRegisteredHorses();

        assertEquals(1, AHDController.registeredHorses.get(0).getId());
        assertEquals(2, AHDController.registeredHorses.get(1).getId());
        assertEquals(3, AHDController.registeredHorses.get(2).getId());
    }


    @Test
    public void testSortRegisteredHorsesByID_UncommonIDs() {
        // Creating a list of Horses
        ArrayList<Horse> horses = new ArrayList<>();

        int horseID1 = Integer.parseInt("003");
        int horseID2 = Integer.parseInt("123");
        int horseID3 = Integer.parseInt("0025");
        horses.add(new Horse(horseID1, "Horse3", "Jockey3", 5, "Breed3", "Record3", "C", null, null));
        horses.add(new Horse(horseID2, "Horse1", "Jockey1", 3, "Breed1", "Record1", "A", null, null));
        horses.add(new Horse(horseID3, "Horse2", "Jockey2", 4, "Breed2", "Record2", "B", null, null));

        // Setting the horses list as registeredHorses list
        AHDController.registeredHorses = horses;

        // Sorting registeredHorses list
        VHDController controller = new VHDController();
        controller.sortRegisteredHorses();

        assertEquals(3, AHDController.registeredHorses.get(0).getId());
        assertEquals(25, AHDController.registeredHorses.get(1).getId());
        assertEquals(123, AHDController.registeredHorses.get(2).getId());
    }
}