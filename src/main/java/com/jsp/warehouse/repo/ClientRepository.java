package com.jsp.warehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse.models.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{

}
