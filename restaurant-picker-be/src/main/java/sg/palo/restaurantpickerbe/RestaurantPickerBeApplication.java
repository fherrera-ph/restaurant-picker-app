package sg.palo.restaurantpickerbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "sg.palo.restaurantpickerbe.*")
public class RestaurantPickerBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantPickerBeApplication.class, args);
	}

}
