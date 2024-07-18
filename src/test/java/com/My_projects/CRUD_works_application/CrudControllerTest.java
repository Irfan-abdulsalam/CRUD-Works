package com.My_projects.CRUD_works_application;

import com.My_projects.CRUD_works_application.controller.CrudController;
import com.My_projects.CRUD_works_application.entity.CrudEntity;
import com.My_projects.CRUD_works_application.service.CrudService;
import com.My_projects.CRUD_works_application.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CrudController.class)
public class CrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrudService crudService;

    @MockBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    private CrudEntity crudEntity;

    @BeforeEach
    void setUp() {
        crudEntity = new CrudEntity();
        crudEntity.setId(1L);
        crudEntity.setName("Test Name");
        crudEntity.setEmail("test@example.com");
        crudEntity.setMobile("1234567890");
        crudEntity.setAddress("Test Address");
        crudEntity.setOpType("CREATE");
    }

    @Test
    void getAllTests() throws Exception {
        Mockito.when(crudService.getAllTests()).thenReturn(Arrays.asList(crudEntity));

        mockMvc.perform(get("/v1/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(crudEntity.getName()));
    }

    @Test
    void getTestById() throws Exception {
        Mockito.when(crudService.getTestById(anyLong())).thenReturn(Optional.of(crudEntity));

        mockMvc.perform(get("/v1/api/get/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(crudEntity.getName()));
    }

    @Test
    void createTest() throws Exception {
        Mockito.when(crudService.createTest(any(CrudEntity.class))).thenReturn(crudEntity);

        mockMvc.perform(post("/v1/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crudEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(crudEntity.getName()));

        // Verify that the email service method was called with the correct arguments
        Mockito.verify(emailService, Mockito.times(1)).sendCrudOperationNotification(
                ArgumentMatchers.eq("New Record Created"),
                ArgumentMatchers.any(CrudEntity.class)
        );
    }

    @Test
    void updateTest() throws Exception {
        Mockito.when(crudService.updateTest(anyLong(), any(CrudEntity.class))).thenReturn(Optional.of(crudEntity));

        mockMvc.perform(put("/v1/api/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crudEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(crudEntity.getName()));

        // Verify that the email service method was called with the correct arguments
        Mockito.verify(emailService, Mockito.times(1)).sendCrudOperationNotification(
                ArgumentMatchers.eq("Record Updated"),
                ArgumentMatchers.any(CrudEntity.class)
        );
    }

    @Test
    void deleteTest() throws Exception {
        Mockito.when(crudService.deleteTest(anyLong())).thenReturn(true);
        mockMvc.perform(delete("/v1/api/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verify that the email service method was called with the correct arguments
        Mockito.verify(emailService, Mockito.times(1)).sendCrudOperationNotification(
                ArgumentMatchers.eq("Record Deleted"),
                ArgumentMatchers.isNull()
        );
    }
}
