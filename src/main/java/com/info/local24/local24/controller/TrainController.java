package com.info.local24.local24.controller;

import com.info.local24.local24.dto.TrainInfoRequest;
import com.info.local24.local24.dto.TrainRequest;
import com.info.local24.local24.dto.TrainUpdateInfoRequest;
import com.info.local24.local24.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class TrainController {


    @Autowired
    private TrainService trainService;

    @PostMapping("/add/train")
    public ResponseEntity<?> addTrain(@Valid @RequestBody TrainRequest trainRequest) {


        return ResponseEntity.ok(trainService.addTrain(trainRequest));

    }

    @PostMapping("/find/trains")
    public ResponseEntity<?> findTrains(@RequestBody TrainInfoRequest trainInfoRequest) {


        return ResponseEntity.ok(trainService.findTrains(trainInfoRequest));
    }

    @DeleteMapping("/{trainNumber}")
    public ResponseEntity<?> removeTrain(@PathVariable String trainNumber) {

        return ResponseEntity.ok(trainService.deleteTrain(trainNumber));
    }

    @PutMapping("/update/train")
    public ResponseEntity<?> updateTrain(@RequestBody TrainUpdateInfoRequest request) {

        return ResponseEntity.ok(trainService.updateTrainDetail(request));
    }


}
