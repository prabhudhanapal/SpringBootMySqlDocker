package com.aarp.spring.repo;

import com.aarp.spring.model.PersonOrder;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PersonOrder, Long> {

}
