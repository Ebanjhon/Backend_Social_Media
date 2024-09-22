package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.SearchUserDTO;
import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.Models.User;
import com.eban.social_media.Repositories.UserRepository;
import com.eban.social_media.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Phương thức để đăng ký người dùng mới
    public User registerUser(User user) {
        // Băm mật khẩu người dùng trước khi lưu vào cơ sở dữ liệu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserDTOByUsername(String username) {
        User u = getUserByUsername(username);
        UserDTO userDTO = new UserDTO(u.getId()
                ,u.getFirstname(), u.getLastname()
                ,u.getUsername(), u.getGender().toString()
                ,u.getEmail(), u.getAvatar(), u.getPhone(), u.getRole().toString() );
        return userDTO;
    }

    @Override
    public List<SearchUserDTO> SearchUser(String text, Long idUser) {
        return userRepository.searchUsersWithFollowStatus(text, idUser);
    }
}
