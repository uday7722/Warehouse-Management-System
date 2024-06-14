package com.jsp.warehouse.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.warehouse.models.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{


	List<Address> findAllByCity(String city);

}
