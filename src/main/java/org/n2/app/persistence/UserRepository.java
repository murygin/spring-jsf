package org.n2.app.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

	@Query("select u from User u where login = :login")
	List<User> findByLogin(@Param("login") String login);

	@Query("select u from User u where email = :email")
	List<User> findByEmail(@Param("email") String email);
	
}
