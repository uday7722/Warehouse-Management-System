package com.jsp.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse.models.Storage;

public interface StorageRepo extends JpaRepository<Storage , Integer>{

}
