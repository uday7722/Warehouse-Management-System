package com.jsp.warehouse.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse.models.Storage;

public interface StorageRepo extends JpaRepository<Storage , Integer>{



}
