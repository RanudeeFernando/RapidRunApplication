package com.example.rapidrunapplication;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RACEControllerTest {

    @Test
    void testSelectingRandomHorsesFromEachGroup_SDD() {
        RACEController controller = new RACEController();

        // Populating groupA, groupB, groupC, groupD with sample data
        List<Horse> groupA = new ArrayList<>();
        List<Horse> groupB = new ArrayList<>();
        List<Horse> groupC = new ArrayList<>();
        List<Horse> groupD = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            groupA.add(new Horse(i, "HorseA", "JockeyA", i, "BreedA", "RaceRecordA", "GroupA", null, null));
            groupB.add(new Horse(5 + i, "HorseB", "JockeyB", i, "BreedB", "RaceRecordB", "GroupB", null, null));
            groupC.add(new Horse(10 + i, "HorseC", "JockeyC", i, "BreedC", "RaceRecordC", "GroupC", null, null));
            groupD.add(new Horse(15 + i, "HorseD", "JockeyD", i, "BreedD", "RaceRecordD", "GroupD", null, null));
        }

        // Assigning groups to the controller
        controller.groupA = groupA;
        controller.groupB = groupB;
        controller.groupC = groupC;
        controller.groupD = groupD;

        // Creating a case where groups are not selected
        controller.groupASelected = false;
        controller.groupBSelected = false;
        controller.groupCSelected = false;
        controller.groupDSelected = false;


        controller.selectRandomHorsesFromEachGroup();

        // Verifying that the groups are selected
        assertTrue(controller.groupASelected);
        assertTrue(controller.groupBSelected);
        assertTrue(controller.groupCSelected);
        assertTrue(controller.groupDSelected);


        // Verifying that the selected horses list has 4 horses
        assertEquals(4, RACEController.selectedHorses.size());
    }


    @Test
    void testAssignRandomRaceTimesBetweenOneAndNinety_InWHD() {
        // Creating Horses
        List<Horse> selectedHorses = new ArrayList<>();
        Horse horse1 = new Horse(1,"Horse1", "Jockey1", 5, "Breed1", "RaceRecord1", "Group1", null, null);
        Horse horse2 = new Horse(2,"Horse2", "Jockey2", 3, "Breed2", "RaceRecord2", "Group2", null, null);
        Horse horse3 = new Horse(3,"Horse3", "Jockey3", 4, "Breed3", "RaceRecord3", "Group3", null, null);

        // Adding horses to selectedHorses list
        selectedHorses.add(horse1);
        selectedHorses.add(horse2);
        selectedHorses.add(horse3);

        // Assigning race times to horses in selectedHorses list
        RACEController.selectedHorses = selectedHorses;
        RACEController controller = new RACEController();
        controller.assignRandomRaceTimesToSelectedHorses();

        assertTrue(horse1.getRaceTime() >= 1 && horse1.getRaceTime() <= 90);
        assertTrue(horse2.getRaceTime() >= 1 && horse2.getRaceTime() <= 90);
        assertTrue(horse3.getRaceTime() >= 1 && horse3.getRaceTime() <= 90);
    }

    @Test
    void testSelectionSort_ByRandomRaceTimes_InWHD() {
        // Creating Horses
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse(1,"Horse1", "Jockey1", 5, "Breed1", "RaceRecord1", "Group1", null, null);
        Horse horse2 = new Horse(2,"Horse2", "Jockey2", 3, "Breed2", "RaceRecord2", "Group2", null, null);
        Horse horse3 = new Horse(3,"Horse3", "Jockey3", 4, "Breed3", "RaceRecord3", "Group3", null, null);
        Horse horse4 = new Horse(4,"Horse3", "Jockey3", 4, "Breed3", "RaceRecord3", "Group3", null, null);

        // Setting race times for horses
        horse1.setRaceTime(72);
        horse2.setRaceTime(35);
        horse3.setRaceTime(24);
        horse4.setRaceTime(18);

        // Adding horses to horses list
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        horses.add(horse4);

        // Calling the selection sort method
        RACEController.selectionSort(horses);

        // Checking if horses get sorted in ascending order based on their race times
        assertEquals(horse4.getId(), horses.get(0).getId());
        assertEquals(horse3.getId(), horses.get(1).getId());
        assertEquals(horse2.getId(), horses.get(2).getId());
        assertEquals(horse1.getId(), horses.get(3).getId());
    }

    @Test
    void testSelectingThreeWinnersFromFourRaceHorses_InWHD() {
        List<Horse> horses = new ArrayList<>();

        // Creating horses
        Horse horse1 = new Horse(1,"Horse1", "Jockey1", 5, "Breed1", "RaceRecord1", "Group1", null, null);
        Horse horse2 = new Horse(2,"Horse2", "Jockey2", 3, "Breed2", "RaceRecord2", "Group2", null, null);
        Horse horse3 = new Horse(3,"Horse3", "Jockey3", 4, "Breed3", "RaceRecord3", "Group3", null, null);
        Horse horse4 = new Horse(4,"Horse3", "Jockey3", 4, "Breed3", "RaceRecord3", "Group3", null, null);

        // Setting race times for each horse
        horse1.setRaceTime(7);
        horse2.setRaceTime(35);
        horse3.setRaceTime(24);
        horse4.setRaceTime(34);

        // Adding horses to horses list
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        horses.add(horse4);

        RACEController.selectionSort(horses);
        RACEController.raceHorses = horses;

        RACEController.selectWinners();

        // Checking if winners are selected
        assertEquals("1st Place", RACEController.winners.get(0).getPlace());
        assertEquals("2nd Place", RACEController.winners.get(1).getPlace());
        assertEquals("3rd Place", RACEController.winners.get(2).getPlace());

        // Checking if winners' IDs match
        assertEquals(1, RACEController.winners.get(0).getId());
        assertEquals(3, RACEController.winners.get(1).getId());
        assertEquals(4, RACEController.winners.get(2).getId());
    }


}
