package br.com.jeyciane.carexpress.interfaces.mapping;

import br.com.jeyciane.carexpress.domain.Passenger;
import br.com.jeyciane.carexpress.domain.PassengerRepository;
import br.com.jeyciane.carexpress.domain.TravelRequest;
import br.com.jeyciane.carexpress.interfaces.input.TravelRequestInput;
import br.com.jeyciane.carexpress.interfaces.output.TravelRequestOutput;
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

    public TravelRequestOutput map(TravelRequest travelRequest) {

        TravelRequestOutput travelRequestOutput = new TravelRequestOutput();

        travelRequestOutput.setId(travelRequest.getId());
        travelRequestOutput.setOrigin(travelRequest.getOrigin());
        travelRequestOutput.setDestination(travelRequest.getDestination());
        travelRequestOutput.setStatus(travelRequest.getStatus());
        travelRequestOutput.setCreationDate(travelRequest.getCreationDate());

        return travelRequestOutput;
    }

}
