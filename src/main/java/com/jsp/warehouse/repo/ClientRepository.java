package com.jsp.warehouse.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse.models.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{

	Optional<Client> findByEmail(String email);

}
