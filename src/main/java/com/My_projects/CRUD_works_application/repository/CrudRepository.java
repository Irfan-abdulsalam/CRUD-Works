package com.My_projects.CRUD_works_application.repository;

import com.My_projects.CRUD_works_application.entity.CrudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<CrudEntity, Long> {
}
