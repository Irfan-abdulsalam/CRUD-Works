package com.My_projects.CRUD_works_application;

import com.My_projects.CRUD_works_application.entity.CrudEntity;
import com.My_projects.CRUD_works_application.repository.CrudRepository;
import com.My_projects.CRUD_works_application.service.CrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class CrudServiceTest {

    @Autowired
    private CrudService crudService;

    @MockBean
    private CrudRepository crudRepository;

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
    void getAllTests() {
        Mockito.when(crudRepository.findAll()).thenReturn(Arrays.asList(crudEntity));

        assertFalse(crudService.getAllTests().isEmpty());
    }

    @Test
    void getTestById() {
        Mockito.when(crudRepository.findById(anyLong())).thenReturn(Optional.of(crudEntity));

        assertTrue(crudService.getTestById(1L).isPresent());
    }

    @Test
    void createTest() {
        Mockito.when(crudRepository.save(any(CrudEntity.class))).thenReturn(crudEntity);

        assertNotNull(crudService.createTest(crudEntity));
    }

    @Test
    void updateTest() {
        Mockito.when(crudRepository.findById(anyLong())).thenReturn(Optional.of(crudEntity));
        Mockito.when(crudRepository.save(any(CrudEntity.class))).thenReturn(crudEntity);

        assertTrue(crudService.updateTest(1L, crudEntity).isPresent());
    }

    @Test
    void deleteTest() {
        Mockito.when(crudRepository.findById(anyLong())).thenReturn(Optional.of(crudEntity));

        assertTrue(crudService.deleteTest(1L));
    }
}
