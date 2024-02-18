package com.info.local24.local24.service;

import com.info.local24.local24.controller.TrainController;
import com.info.local24.local24.dto.TrainInfoRequest;
import com.info.local24.local24.dto.TrainRequest;
import com.info.local24.local24.dto.TrainRequestResponse;
import com.info.local24.local24.entity.Train;
import com.info.local24.local24.repository.TrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class TrainServiceImpTest {
    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImp trainService;

    @InjectMocks
    private TrainController trainController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTrain() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setTrainNumber("12345");
        trainRequest.setName("Test Train");

        when(trainRepository.findByTrainNumber(trainRequest.getTrainNumber())).thenReturn(null);


        TrainRequestResponse response = trainService.addTrain(trainRequest);
        assertFalse(response.isError());
        assertEquals("train is added successfully", response.getMessage());


    }
    @Test
    public void testAddTrain1() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setTrainNumber("12345");
        trainRequest.setName("Test Train");
        when(trainRepository.findByTrainNumber(trainRequest.getTrainNumber())).thenReturn(new Train(trainRequest.getTrainNumber(),trainRequest.getName(),trainRequest.getStation()));

        // Test adding a new train
        TrainRequestResponse response = trainService.addTrain(trainRequest);
        assertTrue(response.isError());
        assertEquals("train already exist with given number", response.getMessage());

    }
    @Test
    public void testAddTrain2() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setTrainNumber("12345");
        trainRequest.setName("Test Train");
        when(trainRepository.findByTrainNumber("12344")).thenReturn(new Train("12344",trainRequest.getName(),trainRequest.getStation()));

        // Test adding a new train
        TrainRequestResponse response = trainService.addTrain(trainRequest);
        assertTrue(response.isError());
        assertEquals("train already exist with given name", response.getMessage());

    }
@Test
public void testDeleteTrain1() {
    TrainRequest trainRequest = new TrainRequest();
    trainRequest.setTrainNumber("12345");
    trainRequest.setName("Test Train");
    when(trainRepository.findByTrainNumber(trainRequest.getTrainNumber())).thenReturn(new Train(trainRequest.getTrainNumber(),trainRequest.getName(),trainRequest.getStation()));

    // Test adding a new train
    TrainRequestResponse response = trainService.deleteTrain("12345");
    assertFalse(response.isError());
    assertEquals("train is deleted successfully", response.getMessage());

}
    @Test
    public void testDeleteTrain2() {
        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setTrainNumber("12345");
        trainRequest.setName("Test Train");
        when(trainRepository.findByTrainNumber(trainRequest.getTrainNumber())).thenReturn(null);

        // Test adding a new train
        TrainRequestResponse response = trainService.deleteTrain("12345");
        assertTrue(response.isError());
        assertEquals("train does not exist with given id" + trainRequest.getTrainNumber(), response.getMessage());

    }
    @Test
    public void testFindTrains() {
        // Mock data
        TrainInfoRequest trainInfoRequest = new TrainInfoRequest();
        trainInfoRequest.setSource("Station A");
        trainInfoRequest.setDestination("Station B");

        Train train1 = new Train();
        train1.setName("Train 1");
        train1.setStation(Arrays.asList("Station A", "Station C", "Station D"));

        Train train2 = new Train();
        train2.setName("Train 2");
        train2.setStation(Arrays.asList("Station A", "Station B", "Station E"));

        List<Train> trainList = new ArrayList<>();
        trainList.add(train1);
        trainList.add(train2);

        // Mock behavior of trainRepository.findAll()
        when(trainRepository.findAll()).thenReturn(trainList);

        // Call the method under test
        List<String> result = trainService.findTrains(trainInfoRequest);

        // Verify that the trainRepository.findAll() was called once
        //verify(trainRepository, times(1)).findAll();

        // Assert the result
        assertEquals(1, result.size()); // We expect only Train 2 to be added to the result list
        assertEquals("Train 2", result.get(0));
    }


}