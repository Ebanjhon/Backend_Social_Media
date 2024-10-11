package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.ProfileDetailDTO;
import com.eban.social_media.DTO.SearchUserDTO;
import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Các phương thức CRUD có sẵn từ JpaRepository
    User findByEmail(String email);
    User findByUsername(String username);

    // Kiểm tra sự tồn tại của user theo email
    boolean existsByEmail(String email);

    // Kiểm tra sự tồn tại của user theo username
    boolean existsByUsername(String username);

//    @Query("SELECT new com.eban.social_media.DTO.SearchUserDTO(u.id, u.username, u.firstname, u.lastname, u.avatar, " +
//            "CASE WHEN (uf.id IS NOT NULL) THEN true ELSE false END, " +  // Kiểm tra nếu currentUser đang theo dõi u
//            "(SELECT COUNT(f.id) FROM UserFollow f WHERE f.userFollow.id = u.id)) " +  // Đếm số lượng người theo dõi u
//            "FROM User u " +
//            "LEFT JOIN UserFollow uf ON uf.userFollow.id = u.id AND uf.user.id = :currentUserId " +
//            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
//            "LOWER(u.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
//            "LOWER(u.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
//    List<SearchUserDTO> searchUsersWithFollowStatus(@Param("searchTerm") String searchTerm, @Param("currentUserId") Long currentUserId);

    @Query("SELECT new com.eban.social_media.DTO.SearchUserDTO(u.id, u.username, u.firstname, u.lastname, u.avatar, " +
            "CASE WHEN (uf.id IS NOT NULL) THEN true ELSE false END, " +  // Kiểm tra nếu currentUser đang theo dõi u
            "(SELECT COUNT(f.id) FROM UserFollow f WHERE f.userFollow.id = u.id)) " +  // Đếm số lượng người theo dõi u
            "FROM User u " +
            "LEFT JOIN UserFollow uf ON uf.userFollow.id = u.id AND uf.user.id = :currentUserId " +
            "WHERE (:searchTerm IS NULL OR :searchTerm = '' OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) ")
    List<SearchUserDTO> searchUsersWithFollowStatus(@Param("searchTerm") String searchTerm, @Param("currentUserId") Long currentUserId);

    // lấy thông tin chi tiết

    @Query("SELECT new com.eban.social_media.DTO.ProfileDetailDTO(" +
            "(SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId), " +             // Đếm số bài viết
            "(SELECT COUNT(f) FROM UserFollow f WHERE f.userFollow.id = :userId), " +  // Đếm số người theo dõi (followers)
            "(SELECT COUNT(f) FROM UserFollow f WHERE f.user.id = :userId))"           // Đếm số người đang theo dõi (following)
    )
    ProfileDetailDTO getProfileDetail(@Param("userId") Long userId);

//     quản lý user
@Query("SELECT new com.eban.social_media.DTO.UserDTO(u.id, u.firstname, u.lastname, u.username, u.gender, u.email, u.avatar, u.phone, u.birthDate, u.active) " +
        "FROM User u WHERE " +
        "(:text IS NULL OR :text = '' OR u.username LIKE %:text% OR u.firstname LIKE %:text% OR u.lastname LIKE %:text%)")
Page<UserDTO> findUsersWithFilter(@Param("text") String text, Pageable pageable);

}