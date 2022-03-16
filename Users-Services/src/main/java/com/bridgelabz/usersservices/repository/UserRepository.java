package com.bridgelabz.usersservices.repository;


import com.bridgelabz.usersservices.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select email from user_data where email=:email",nativeQuery = true)
    String getEmail(String email);
    @Query(value = "select password from user_data where email=:email",nativeQuery = true)
    String getPassword(String email);
    @Query(value = "select user_id from user_data where email=:email",nativeQuery = true)
    Long getId(String email);
}
