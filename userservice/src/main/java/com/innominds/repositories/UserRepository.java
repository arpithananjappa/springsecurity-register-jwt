package com.innominds.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innominds.data.User;
import com.innominds.document.UserDocument;
//import com.innominds.document.UserDocument;

@Repository
public interface UserRepository extends MongoRepository<UserDocument,String>{
	//Optional<User> findUserByEmail(String email);
//	@Query("{ 'email' : ?0 }")
//	User findByEmail(String email);
//	
	
//	@Query("{ 'username' : ?0 }")
//	User findByEmail(String username);
	@Query("{ 'username' : ?0 }")
	UserDocument findByUsername(String username);

}
