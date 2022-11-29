package com.example.labinventtaskbackend.services.impls;

import com.example.labinventtaskbackend.exception.OperationFailedException;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.repositories.RoleRepository;
import com.example.labinventtaskbackend.repositories.UserRepository;
import com.example.labinventtaskbackend.services.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public List<User> findAll() {
        List<User> users;
        try {
            users = userRepository.findAll();
        }catch (Exception exception){
            throw new OperationFailedException("findAll failed");
        }
        log.info("IN findAll users: {} were successfully received", users);
        return users;
    }

    @SneakyThrows
    @Override
    public User findByUsername(String username) {
        User user;
        try {
            user = userRepository.findByUsername(username);
        }catch (Exception exception){
            throw new OperationFailedException("findByUsername failed");
        }
        log.info("IN findAll user: {} was successfully received by username: {}", user, username);
        return user;
    }

    @SneakyThrows
    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new OperationFailedException("findById failed"));
        log.info("IN findAll user: {} was successfully received by id: {}", user, id);
        return user;
    }

    @SneakyThrows
    @Override
    public Long deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception exception){
            throw new OperationFailedException("deleteById failed");
        }
        log.info("IN deleteById user was successfully deleted by id: {}", id);
        return id;
    }
}
