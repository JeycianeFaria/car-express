package br.com.jeyciane.carexpress.interfaces.incoming;

import br.com.jeyciane.carexpress.domain.Driver;
import br.com.jeyciane.carexpress.domain.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RestController
@RequestMapping(path = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverAPI {

    @Autowired
    DriverRepository driverRepository;

    @GetMapping
    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/{id}")
    public Driver findDriver(@PathVariable Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    @PutMapping("/{id}")
    public Driver fullUpdateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);

        foundDriver.setName(driver.getName());
        foundDriver.setBirthDate(driver.getBirthDate());

        return driverRepository.save(foundDriver);
    }

    @PatchMapping("/{id}")
    public Driver incrementalUpdateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);

        foundDriver.setName(ofNullable(driver.getName()).orElse(foundDriver.getName()));
        foundDriver.setBirthDate(ofNullable(driver.getBirthDate()).orElse(foundDriver.getBirthDate()));

        return driverRepository.save(foundDriver);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverRepository.delete(findDriver(id));
    }

}
