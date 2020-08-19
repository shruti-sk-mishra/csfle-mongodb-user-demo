package com.shr.users.repositories;

import com.shr.users.models.User;
import org.bson.BsonBinary;
import org.bson.BsonString;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author shruti.mishra
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserName(String userName);

    public List<User> findAllByPassword(BsonBinary password);
}
