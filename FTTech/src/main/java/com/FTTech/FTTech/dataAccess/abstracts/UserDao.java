package com.FTTech.FTTech.dataAccess.abstracts;

import com.FTTech.FTTech.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    boolean existsByUserEmail(String email);
}
