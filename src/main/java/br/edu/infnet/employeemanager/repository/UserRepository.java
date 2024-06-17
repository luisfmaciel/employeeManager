package br.edu.infnet.employeemanager.repository;

import br.edu.infnet.employeemanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByName(String username);
}
