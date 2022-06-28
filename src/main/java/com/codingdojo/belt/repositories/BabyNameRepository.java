package com.codingdojo.belt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.belt.models.BabyName;
import com.codingdojo.belt.models.User;

@Repository

public interface BabyNameRepository extends CrudRepository<BabyName, Long> {
    

    List<BabyName> findAll();

    
    Optional<BabyName> findByNewName(String name);
   
    // not yet voted names
    List<BabyName> findByUsersNotContains(User user);
    
    // voted names
    List<BabyName> findAllBabyNamesByUsers(User user);
    
    
}
