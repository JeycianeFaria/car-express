package br.com.jeyciane.carexpress.interfaces.incoming.mapping;

import br.com.jeyciane.carexpress.domain.Passenger;
import br.com.jeyciane.carexpress.domain.PassengerRepository;
import br.com.jeyciane.carexpress.domain.TravelRequest;
import br.com.jeyciane.carexpress.interfaces.incoming.PassengerAPI;
import br.com.jeyciane.carexpress.interfaces.incoming.input.TravelRequestInput;
import br.com.jeyciane.carexpress.interfaces.incoming.output.TravelRequestOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    public EntityModel<TravelRequestOutput> buildOutputModel(
            TravelRequest travelRequest, TravelRequestOutput travelRequestOutput) {

        Link passengerLink = WebMvcLinkBuilder.linkTo(PassengerAPI.class)
                .slash(travelRequest.getPassenger().getId())
                .withRel("passenger")
                .withTitle(travelRequest.getPassenger().getName());

        EntityModel<TravelRequestOutput> model = EntityModel.of(travelRequestOutput);
        model.add(passengerLink);

        return model;
    }

}
