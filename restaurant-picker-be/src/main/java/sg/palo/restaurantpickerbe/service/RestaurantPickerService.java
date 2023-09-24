package sg.palo.restaurantpickerbe.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RestaurantPickerService {

    private Random random = new Random();

    public String getRestaurantFromList(List<String> restaurants) {

        return restaurants.get(random.nextInt(restaurants.size()));

    }
}
