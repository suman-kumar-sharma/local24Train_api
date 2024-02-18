package com.info.local24.local24.repository;

import com.info.local24.local24.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    public Train findByTrainNumber(String trainNumber);
}
