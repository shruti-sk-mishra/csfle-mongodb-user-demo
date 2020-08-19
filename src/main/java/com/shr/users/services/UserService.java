package com.shr.users.services;

import com.shr.fle.encryptions.EncryptionOptions;
import com.shr.fle.keymanagement.KMSHandler;
import com.shr.users.models.User;
import com.shr.users.repositories.UserRepository;
import org.bson.BsonBinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author shruti.mishra
 */
@Service
public class UserService {


    @Autowired
    private KMSHandler kmsHandler;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> findAllByPassword(String password) {
        return this.userRepository.findAllByPassword(getEncryptedPassword(password));
    }

    public User findByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }
    public User addNewUser(User user) {
        return userRepository.save(user);
    }



    private BsonBinary getEncryptedPassword(String password){
        return kmsHandler.getClientEncryption().encrypt(new BsonBinary(password.getBytes()),
                EncryptionOptions.get(EncryptionOptions.DETERMINISTIC_ENCRYPTION_TYPE,
                        kmsHandler.getEncryptionKeyUUID()));
    }
}