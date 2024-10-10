package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.ManaUserDTO;
import com.eban.social_media.DTO.ProfileDetailDTO;
import com.eban.social_media.DTO.SearchUserDTO;
import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.Models.User;
import com.eban.social_media.Repositories.UserRepository;
import com.eban.social_media.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Lazy
@Service
@Transactional
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
                ,u.getUsername(), u.getGender()
                ,u.getEmail(), u.getAvatar(), u.getPhone(), u.getRole().toString(), u.getBirthDate(), u.isActive() );
        return userDTO;
    }

    @Override
    public List<SearchUserDTO> SearchUser(String text, Long idUser) {
        return userRepository.searchUsersWithFollowStatus(text, idUser);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = getUserById(userDTO.getId());
        user.setFirstname(userDTO.getFirstName());
        user.setLastname(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setBirthDate(userDTO.getBirth());
        userRepository.save(user);
    }

    @Override
    public void updateAvatar(User user, String avatar) {
        user.setAvatar(avatar); // Cập nhật avatar
        userRepository.save(user);
    }

    @Override
    public ProfileDetailDTO getProfileDetail(Long userId) {
        return userRepository.getProfileDetail(userId);
    }

    @Override
    public void activateUser(Long userId) {
        User user = getUserById(userId);
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void unActivateUser(Long userId) {
        User user = getUserById(userId);
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public List<ManaUserDTO> getManaUsers(String text, Pageable pageable) {
        List<ManaUserDTO> manaUsers = new ArrayList<>();
        Page<UserDTO> usersPage = userRepository.findUsersWithFilter(text, pageable);
        List<UserDTO> usersList = usersPage.getContent(); // Sử dụng getContent() để lấy danh sách các phần tử

        for (UserDTO user : usersList) {
            ManaUserDTO manaUser = new ManaUserDTO();
            ProfileDetailDTO profileDetailDTO = userRepository.getProfileDetail(user.getId());
            manaUser.setUserDTO(user);
            manaUser.setProfileDetailDTO(profileDetailDTO);
            manaUsers.add(manaUser);
        }
        return manaUsers;
    }
}
