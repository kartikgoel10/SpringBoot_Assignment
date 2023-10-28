package com.test.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.User;
import com.test.repository.UserRepository;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(String commentFrom, String commentTo) {
        if (!isValidUserName(commentFrom) || !isValidUserName(commentTo)) {
            throw new IllegalArgumentException("Invalid Request - Invalid user names");
        }

        Optional<User> existingUser = userRepository.findByCommentTo(commentTo);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        User newUser = new User();
        newUser.setCommentFrom(commentFrom);
        newUser.setCommentTo(commentTo);

        return userRepository.save(newUser);
    }

    private boolean isValidUserName(String userName) {
        return !userName.isEmpty() && userName.matches("^[a-zA-Z]+$");
    }
}
