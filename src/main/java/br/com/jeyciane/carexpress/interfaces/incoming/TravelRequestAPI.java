package br.com.jeyciane.carexpress.interfaces.incoming;

import br.com.jeyciane.carexpress.domain.TravelRequest;
import br.com.jeyciane.carexpress.domain.TravelService;
import br.com.jeyciane.carexpress.interfaces.incoming.input.TravelRequestInput;
import br.com.jeyciane.carexpress.interfaces.incoming.mapping.TravelRequestMapper;
import br.com.jeyciane.carexpress.interfaces.incoming.output.TravelRequestOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RestController
@RequestMapping(path = "/travelRequests", produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelRequestAPI {

    @Autowired
    TravelService travelService;
    @Autowired
    TravelRequestMapper mapper;


    @PostMapping
    public EntityModel<TravelRequestOutput> makeTravelRequest(@RequestBody TravelRequestInput travelRequestInput) {

        TravelRequest request = travelService.saveTravelRequest(mapper.map(travelRequestInput));

        TravelRequestOutput output = mapper.map(request);

        return mapper.buildOutputModel(request, output);
    }

    @GetMapping("/nearby")
    public List<EntityModel<TravelRequestOutput>> listNearbyRequests(@RequestParam String currentAddress) {

        List<TravelRequest> requests = travelService.listNearbyTravelRequests(currentAddress);

        return mapper.buildOutputModel(requests);
    }

}
