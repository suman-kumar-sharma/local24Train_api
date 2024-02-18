package com.info.local24.local24.service;

import com.info.local24.local24.dto.TrainInfoRequest;
import com.info.local24.local24.dto.TrainRequest;
import com.info.local24.local24.dto.TrainRequestResponse;
import com.info.local24.local24.dto.TrainUpdateInfoRequest;

import java.util.List;

public interface TrainService {
    public TrainRequestResponse addTrain(TrainRequest trainRequest);
    public TrainRequestResponse deleteTrain(String trainNumber);
    public List<String> findTrains(TrainInfoRequest trainInfoRequest);
    public TrainRequestResponse updateTrainDetail(TrainUpdateInfoRequest trainUpdateInfoRequest);

}
