package co.com.elkin.apps.bookingapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {

    /**
     *
     * @param test this is a test parameter
     * @return some response description
     */
    @GetMapping
    @Operation(summary = "This is the summary", description = "Some description")
    public String obtainGreeting(String test){
        return "Hello Word";
    }

}
