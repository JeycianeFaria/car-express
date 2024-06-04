package br.com.jeyciane.carexpress.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TravelService {

    @Autowired
    TravelRequestRepository travelRequestRepository;

    public TravelRequest saveTravelRequest(TravelRequest travelRequest) {

        travelRequest.setStatus(TravelRequestStatus.CREATED);
        travelRequest.setCreationDate(new Date());

        return travelRequestRepository.save(travelRequest);
    }

}
