package br.com.jeyciane.carexpress.interfaces.outcoming;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GMapsService {

    @Value("${app.car.domain.googlemaps.apikey}")
    private String apiKey;

    private static final String GMAPS_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json" +
            "?origin={origin}&destination={destination}&key={key}";

    public Integer getDistanceBetweenAddresses(String addressOne, String addressTwo) {

        RestTemplate template = new RestTemplate();
        String jsonResult = template.getForObject(GMAPS_TEMPLATE, String.class, addressOne, addressTwo, apiKey);

        List<Integer> results = JsonPath.parse(jsonResult).read("$..legs[*].duration.value");

        return results.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }

}
