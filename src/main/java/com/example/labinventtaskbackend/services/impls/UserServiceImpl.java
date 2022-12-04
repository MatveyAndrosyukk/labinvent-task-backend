package com.example.labinventtaskbackend.services.impls;

import com.example.labinventtaskbackend.exception.OperationFailedException;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.repositories.UserRepository;
import com.example.labinventtaskbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        log.info("IN findAll users: {} were successfully received", users);
        return users;
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);;
        log.info("IN findAll user: {} was successfully received by username: {}", user, username);
        return user;
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new OperationFailedException("findById failed"));
        log.info("IN findAll user: {} was successfully received by id: {}", user, id);
        return user;
    }

    @SneakyThrows
    @Transactional
    @Override
    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("IN deleteById user was successfully deleted by id: {}", id);
        return id;
    }
}
