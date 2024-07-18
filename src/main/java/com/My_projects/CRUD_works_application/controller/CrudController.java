package com.My_projects.CRUD_works_application.controller;

import com.My_projects.CRUD_works_application.entity.CrudEntity;
import com.My_projects.CRUD_works_application.service.CrudService;
import com.My_projects.CRUD_works_application.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CrudController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CrudController.class);

    @Autowired
    private CrudService crudService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<CrudEntity>> getAllTests() {
        logger.info("Getting all records from crudEntities");
        List<CrudEntity> crudEntities = crudService.getAllTests();
        return ResponseEntity.ok(crudEntities);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CrudEntity> getTestById(@PathVariable Long id) {
        logger.info("Getting test with ID: {}", id);
        return crudService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CrudEntity> createTest(@RequestBody CrudEntity crudEntity) {
        logger.info("Creating crudEntity: {}", crudEntity);
        CrudEntity createdCrudEntity = crudService.createTest(crudEntity);
        emailService.sendCrudOperationNotification("New Record Created", createdCrudEntity);
        return ResponseEntity.ok(createdCrudEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrudEntity> updateTest(@PathVariable Long id, @RequestBody CrudEntity crudEntity) {
        logger.info("Updating crudEntity with ID: {}", id);
        return crudService.updateTest(id, crudEntity)
                .map(updatedEntity -> {
                    emailService.sendCrudOperationNotification("Record Updated", updatedEntity);
                    return ResponseEntity.ok(updatedEntity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        logger.info("Deleting test with ID: {}", id);
        if (crudService.deleteTest(id)) {
            emailService.sendCrudOperationNotification("Record Deleted", null);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
