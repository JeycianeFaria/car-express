package br.com.jeyciane.carexpress.domain;

import br.com.jeyciane.carexpress.interfaces.outcoming.GMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravelService {

    private static final int MAX_TRAVEL_TIME = 600;

    @Autowired
    TravelRequestRepository travelRequestRepository;
    @Autowired
    GMapsService gMapsService;


    public TravelRequest saveTravelRequest(TravelRequest travelRequest) {

        travelRequest.setStatus(TravelRequestStatus.CREATED);
        travelRequest.setCreationDate(new Date());

        return travelRequestRepository.save(travelRequest);
    }

    public List<TravelRequest> listNearbyTravelRequests(String currentAddress) {
        List<TravelRequest> requests = travelRequestRepository.findByStatus(TravelRequestStatus.CREATED);

        return requests.stream()
                .filter(tr -> gMapsService.getDistanceBetweenAddresses(currentAddress, tr.getOrigin()) < MAX_TRAVEL_TIME)
                .collect(Collectors.toList());
    }

}
