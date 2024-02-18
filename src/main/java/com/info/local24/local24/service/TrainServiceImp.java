package com.info.local24.local24.service;

import com.info.local24.local24.dto.TrainInfoRequest;
import com.info.local24.local24.dto.TrainRequest;
import com.info.local24.local24.dto.TrainRequestResponse;
import com.info.local24.local24.dto.TrainUpdateInfoRequest;
import com.info.local24.local24.entity.Train;
import com.info.local24.local24.repository.TrainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class TrainServiceImp implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public TrainRequestResponse addTrain(TrainRequest trainRequest) {
        Train train = trainRepository.findByTrainNumber(trainRequest.getTrainNumber());
        if (train != null) {
            log.info("train already exist with given train Number");
            return TrainRequestResponse.builder().error(true).message("train already exist with given number").build();
        }
        try {
            train = mapTo(trainRequest);
            trainRepository.save(train);

        } catch (Exception e) {
            return TrainRequestResponse.builder().error(true).message("train already exist with given name").build();
        }
        return TrainRequestResponse.builder().trainRequest(trainRequest).error(false).message("train is added successfully").build();
    }

    @Override
    public TrainRequestResponse deleteTrain(String trainNumber) {
        Train train = trainRepository.findByTrainNumber(trainNumber);
        if (train != null) {
            log.info("train is deleted successfully");
            trainRepository.delete(train);
            return TrainRequestResponse.builder().error(false).message("train is deleted successfully").build();
        }
        return TrainRequestResponse.builder().error(true).message("train does not exist with given id" + trainNumber).build();
    }

    @Override
    public List<String> findTrains(TrainInfoRequest trainInfoRequest) {

        List<Train> trainList = trainRepository.findAll();

        List<String> result = new ArrayList<>();
        log.info(trainInfoRequest.getSource() + "*" + trainInfoRequest.getDestination());

        for (Train train : trainList) {
            log.info(train.getStation().toString());
            List<String> res = train.getStation().stream().filter(s -> s.equals(trainInfoRequest.getSource()) || s.equals(trainInfoRequest.getDestination())).collect(Collectors.toList());
            log.info(res.toString());
            if (res.size() == 2) result.add(train.getName());
        }

        return result;
    }

    @Override
    public TrainRequestResponse updateTrainDetail(TrainUpdateInfoRequest trainUpdateInfoRequest) {
        Train train = trainRepository.findByTrainNumber(trainUpdateInfoRequest.getTrainRequest().getTrainNumber());
        if (train == null) {
            log.info("train does not exist with given train Number");
            return TrainRequestResponse.builder().error(true).message("train does not exist with given train Number " + trainUpdateInfoRequest.getTrainRequest().getTrainNumber()).build();
        }
        try {
            train.setName(trainUpdateInfoRequest.getTrainRequest().getName());
            if (trainUpdateInfoRequest.getQueryType().equals("add")) {
                List<String> addStation = trainUpdateInfoRequest.getTrainRequest().getStation();
                for (String station : addStation) {
                    int flag = 0;
                    for (String s : train.getStation()) {

                        if (station.equals(s)) {
                            flag++;
                            break;
                        }
                    }
                    if (flag == 0) {
                        train.getStation().add(station);
                    }
                }
                trainRepository.save(train);

            } else {
                List<String> removeStation = trainUpdateInfoRequest.getTrainRequest().getStation();
                for (String station : removeStation) {

                    for (String s : train.getStation()) {

                        if (station.equals(s)) {
                            train.getStation().remove(s);
                            break;
                        }
                    }
                }
                trainRepository.save(train);
            }

        } catch (Exception e) {
            return TrainRequestResponse.builder().error(true).message("train already exist with given name").build();
        }
        return TrainRequestResponse.builder().error(false).trainRequest(mapToDTO(train)).message("train detail updated").build();
    }

    private Train mapTo(TrainRequest trainRequest) {

        Train train = new Train();
        train.setTrainNumber(trainRequest.getTrainNumber());
        train.setName(trainRequest.getName());
        train.setStation(trainRequest.getStation());
        return train;
    }

    private TrainRequest mapToDTO(Train train) {

        TrainRequest trainRequest = new TrainRequest();

        trainRequest.setName(train.getName());
        trainRequest.setStation(train.getStation());
        trainRequest.setTrainNumber(train.getTrainNumber());
        return trainRequest;
    }
}
