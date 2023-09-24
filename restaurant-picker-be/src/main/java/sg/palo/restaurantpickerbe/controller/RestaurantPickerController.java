package sg.palo.restaurantpickerbe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sg.palo.restaurantpickerbe.service.RestaurantPickerService;

import java.util.List;

@RestController
public class RestaurantPickerController {

    @Autowired
    private RestaurantPickerService restaurantPickerService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/pick")
    public ResponseEntity<String> getRestaurantFromList(
            @RequestParam(name="restaurants", required = true) List<String> restaurants) {


        return restaurants.isEmpty()
                ? ResponseEntity.badRequest().body("No restaurant provided")
                : ResponseEntity.ok(restaurantPickerService.getRestaurantFromList(restaurants));
    }

}
