package com.example.optics.services;

import com.example.optics.models.Role;
import com.example.optics.models.User;
import com.example.optics.repository.RoleRepository;
import com.example.optics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Класс-сервис для пользователя
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Загрузить пользователя по его почте
     * @param s
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);

        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

    /**
     * Найти пользователя по его почте
     * @param email
     * @return User
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Добавить пользователя в БД
     * @param user
     * @return boolean
     */
    public boolean saveUser(User user) {
        User userDb = userRepository.findByEmail(user.getEmail());

        if (userDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    /**
     * Удалить пользователя из БД
     * @param userId
     * @return boolean
     */
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    /**
     * Найти пользователя по id
     * @param userId
     * @return User
     */
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
