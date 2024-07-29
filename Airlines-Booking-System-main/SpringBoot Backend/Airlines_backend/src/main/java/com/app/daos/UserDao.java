package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.UserDetailsEntity;

public interface UserDao extends JpaRepository<UserDetailsEntity,Integer> {

    @Query(value="select * from user_details_entity where email=:e",nativeQuery = true)
    UserDetailsEntity findByEmail(@Param("e")String e);

    @Query(value = "select * from user_details_entity",nativeQuery = true)
    List<UserDetailsEntity> findAllUsers();

    @Modifying
    @Query(value = "update user_details_entity set cpass=:cpass, email=:email, name=:name where id = :customerId",nativeQuery = true)
    void editProfile(@Param("cpass") String cpass,@Param("email")String email,@Param("name")String name, @Param("customerId") Integer customerId);

    @Query(value="select * from user_details_entity where id=:cid",nativeQuery = true)
    UserDetailsEntity findCustomerById(@Param("cid")Integer cid);


    @Query(value = "select passenger_id_id from user_details_passenger_id where user_details_id = :cid", nativeQuery = true)
    List<Integer> findAllPassengersByCid(@Param("cid") Integer cid);

    
    
}
