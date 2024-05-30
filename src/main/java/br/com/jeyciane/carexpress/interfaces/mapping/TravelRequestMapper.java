package br.com.jeyciane.carexpress.interfaces.mapping;

import br.com.jeyciane.carexpress.domain.Passenger;
import br.com.jeyciane.carexpress.domain.PassengerRepository;
import br.com.jeyciane.carexpress.domain.TravelRequest;
import br.com.jeyciane.carexpress.interfaces.input.TravelRequestInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class TravelRequestMapper {

    @Autowired
    private PassengerRepository passengerRepository;


    public TravelRequest map(TravelRequestInput input) {

        Passenger passenger = passengerRepository.findById(input.getPassengerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TravelRequest travelRequest = new TravelRequest();
        travelRequest.setOrigin(input.getOrigin());
        travelRequest.setDestination(input.getDestination());
        travelRequest.setPassenger(passenger);

        return travelRequest;

    }

}
