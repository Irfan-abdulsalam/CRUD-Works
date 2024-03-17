package com.My_projects.CRUD_operation_application.controller;

import com.My_projects.CRUD_operation_application.CRUD_entity.Test;
import com.My_projects.CRUD_operation_application.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping
    public List<Test> getAllTests() {
        System.out.println("Getting all records from tests");
        return testRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable Long id) {
        System.out.println("Getting test with ID: " + id);
        return testRepository.findById(id);
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        System.out.println("Creating test: " + test);
        return testRepository.save(test);
    }

    @PutMapping("/{id}")
    public Test updateTest(@PathVariable Long id, @RequestBody Test test) {
        System.out.println("Updating test with ID: " + id);
        Test existingTest = testRepository.findById(id).orElse(null);
        if (existingTest == null) {
            System.out.println("Test not found, skipping update");
            return null;
        }
        existingTest.setName(test.getName());
        existingTest.setEmail(test.getEmail());
        existingTest.setMobile(test.getMobile());
        existingTest.setAddress(test.getAddress());
        existingTest.setOpType(test.getOpType());
        return testRepository.save(existingTest);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        System.out.println("Deleting test with ID: " + id);
        Test test = testRepository.findById(id).orElse(null);
        if (test == null) {
            System.out.println("Test not found, skipping delete");
            return;
        }
        testRepository.delete(test);
    }
}