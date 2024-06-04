package br.com.jeyciane.carexpress.interfaces.output;

import br.com.jeyciane.carexpress.domain.TravelRequestStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TravelRequestOutput {

    Long id;
    String origin;
    String destination;
    TravelRequestStatus status;
    Date creationDate;

}
