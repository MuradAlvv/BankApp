package com.example.rest.repositories;

import com.example.rest.entities.User;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String userName);

}
