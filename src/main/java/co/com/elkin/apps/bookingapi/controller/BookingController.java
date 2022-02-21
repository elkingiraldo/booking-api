package co.com.elkin.apps.bookingapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {

    @GetMapping
    public String obtainGreeting(){
        return "Hello Word";
    }

}
