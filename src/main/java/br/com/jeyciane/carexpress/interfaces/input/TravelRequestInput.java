package br.com.jeyciane.carexpress.interfaces.input;

import lombok.Data;

@Data
public class TravelRequestInput {

    Long passengerId;
    String origin;
    String destination;

}
