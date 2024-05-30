package br.com.jeyciane.carexpress.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class TravelRequest {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Passenger passenger;
    String origin;
    String destination;

}
