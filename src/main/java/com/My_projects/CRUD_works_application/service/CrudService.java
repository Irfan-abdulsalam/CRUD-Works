package com.My_projects.CRUD_works_application.service;

import com.My_projects.CRUD_works_application.entity.CrudEntity;
import com.My_projects.CRUD_works_application.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    @Autowired
    private CrudRepository crudRepository;

    public List<CrudEntity> getAllTests() {
        return crudRepository.findAll();
    }

    public Optional<CrudEntity> getTestById(Long id) {
        return crudRepository.findById(id);
    }

    public CrudEntity createTest(CrudEntity crudEntity) {
        return crudRepository.save(crudEntity);
    }

    public Optional<CrudEntity> updateTest(Long id, CrudEntity crudEntity) {
        return crudRepository.findById(id).map(existingCrudEntity -> {
            existingCrudEntity.setName(crudEntity.getName());
            existingCrudEntity.setEmail(crudEntity.getEmail());
            existingCrudEntity.setMobile(crudEntity.getMobile());
            existingCrudEntity.setAddress(crudEntity.getAddress());
            existingCrudEntity.setOpType(crudEntity.getOpType());
            return crudRepository.save(existingCrudEntity);
        });
    }

    public boolean deleteTest(Long id) {
        return crudRepository.findById(id).map(crudEntity -> {
            crudRepository.delete(crudEntity);
            return true;
        }).orElse(false);
    }
}
