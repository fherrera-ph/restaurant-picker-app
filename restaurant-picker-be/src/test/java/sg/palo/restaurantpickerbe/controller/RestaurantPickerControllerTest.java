package sg.palo.restaurantpickerbe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import sg.palo.restaurantpickerbe.service.RestaurantPickerService;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {RestaurantPickerService.class})
@Import(RestaurantPickerController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class RestaurantPickerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantPickerService restaurantPickerService;

    @Test
    void hello() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnOnlyOneResult() throws Exception {
        this.mockMvc.perform(get("/pick")
                .queryParam("restaurants","mcdonalds"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString().matches("^mcdonalds$");
    }

    @Test
    void shouldReturnAnyResult() throws Exception {
        this.mockMvc.perform(get("/pick")
                .queryParam("restaurants","mcdonalds,burgerking,wingstop"))
                .andExpect(status().isOk())
                .andDo(document("home"));
    }
}
