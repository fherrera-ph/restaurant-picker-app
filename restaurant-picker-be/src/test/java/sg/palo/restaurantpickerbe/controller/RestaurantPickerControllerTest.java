package sg.palo.restaurantpickerbe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import sg.palo.restaurantpickerbe.service.RestaurantPickerService;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {RestaurantPickerService.class})
@Import(RestaurantPickerController.class)
@AutoConfigureRestDocs
public class RestaurantPickerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantPickerService restaurantPickerService;

    @Test
    void shouldReturnOnlyOneResultFromOneData() throws Exception {
        this.mockMvc.perform(get("/pick")
                        .queryParam("restaurants","mcdonalds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("mcdonalds"));
    }
    @Test
    void shouldReturnOnlyOneResultFromMultipleData() throws Exception {
        this.mockMvc.perform(get("/pick")
                .queryParam("restaurants","mcdonalds,burgerking,wingstop"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(org.hamcrest.Matchers.containsString("{\"result\"")))
                .andDo(document("restaurant-picker-doc",
                        preprocessResponse(prettyPrint())));
    }


    @Test
    void shouldReturnError() throws Exception {
        this.mockMvc.perform(get("/pick"))
                .andExpect(status().is4xxClientError())
                .andDo(document("restaurant-picker-doc-error",
                        preprocessResponse(prettyPrint())));
    }
}
