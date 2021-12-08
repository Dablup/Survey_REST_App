package com.example.akvelon.akvelon;

import com.example.akvelon.akvelon.model.Survey;
import com.example.akvelon.akvelon.repository.SurveyRepository;
import com.example.akvelon.akvelon.service.SurveyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class AkvelonApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SurveyService surveyService;

    @MockBean
    private SurveyRepository surveyRepository;

    Survey survey1 = new Survey("Test1", new Date(20201203), new Date(20250132), true);
    Survey survey2 = new Survey("Test2", new Date(20211203), new Date(20250522), true);
    Survey survey3 = new Survey("Test3", new Date(20191203), new Date(20450132), true);

    @Test
    public void getAllSurveys_success() throws Exception {
        List<Survey> records = new ArrayList<>(Arrays.asList(survey1, survey2, survey3));
        Mockito.when(surveyRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/survey/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test3"));
    }

    @Test
    public void createSurvey_success() throws Exception {
        Survey survey3 = new Survey("Test3", new Date(20191203), new Date(20450132), true);

        Mockito.when(surveyRepository.save(survey3)).thenReturn(survey3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/survey/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(survey3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void updateSurvey_success() throws Exception {
        Survey survey3 = new Survey("Test3", new Date(20191203), new Date(20450132), true);

        Mockito.when(surveyRepository.getById(survey1.getId())).thenReturn(survey1);
        Mockito.when(surveyRepository.save(survey3)).thenReturn(survey3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.patch("/survey/update/0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(survey3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }


    @Test
    public void deleteSurveyById_success() throws Exception {
        Mockito.when(surveyRepository.findById(survey2.getId())).thenReturn(Optional.of(survey2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/survey/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
