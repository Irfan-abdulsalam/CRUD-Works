package com.My_projects.CRUD_operation_application.repository;

import com.My_projects.CRUD_operation_application.CRUD_entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}