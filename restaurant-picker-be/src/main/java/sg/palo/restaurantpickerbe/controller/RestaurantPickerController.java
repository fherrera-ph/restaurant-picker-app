package sg.palo.restaurantpickerbe.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sg.palo.restaurantpickerbe.service.RestaurantPickerService;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantPickerController {

    @Autowired
    private RestaurantPickerService restaurantPickerService;

    @GetMapping("/pick")
    public ResponseEntity<String> getRestaurantFromList(
            @RequestParam(name="restaurants", required = true) List<String> restaurants) {


        JSONObject jsonObject = new JSONObject();
        if(restaurants.isEmpty()) {
            return ResponseEntity.badRequest().body(jsonObject.put("result", "").toString());
        }

        String result = restaurantPickerService.getRestaurantFromList(restaurants);
        jsonObject.put("result", result);
        return ResponseEntity.ok(jsonObject.toString());
    }

}
