package br.com.jeyciane.carexpress.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Passenger {

    @Id
    @GeneratedValue
    Long id;
    String name;

}
