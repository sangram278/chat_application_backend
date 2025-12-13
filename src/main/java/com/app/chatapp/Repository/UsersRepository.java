package com.app.chatapp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.chatapp.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	 Optional<Users> findByPhone(String phone);
	 
	 @Query("SELECT u FROM Users u WHERE u.phone IN :phones")
	 List<Users> findRegisteredContacts(@Param("phones") List<String> phones);

}
