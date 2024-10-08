package com.eban.social_media.Services;

import com.eban.social_media.DTO.ProfileDetailDTO;
import com.eban.social_media.DTO.SearchUserDTO;
import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.Models.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    // Kiểm tra sự tồn tại của user theo email
    boolean existsByEmail(String email);

    // Kiểm tra sự tồn tại của user theo username
    boolean existsByUsername(String username);

    User getUserByUsername(String username);

    UserDTO getUserDTOByUsername(String username);

    List<SearchUserDTO> SearchUser(String text, Long idUserCurent);

    void updateUser(UserDTO user);

    void updateAvatar(User user, String avatar);

    ProfileDetailDTO getProfileDetail(Long userId);

    void activateUser(Long userId);

}
