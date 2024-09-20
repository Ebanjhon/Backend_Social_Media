package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.SearchUserDTO;
import com.eban.social_media.Models.User;
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

    @Query("SELECT new com.eban.social_media.DTO.SearchUserDTO(u.id, u.username, u.firstname, u.lastname, u.avatar, " +
            "CASE WHEN (uf.id IS NOT NULL) THEN true ELSE false END, " +  // Kiểm tra nếu currentUser đang theo dõi u
            "(SELECT COUNT(f.id) FROM UserFollow f WHERE f.userFollow.id = u.id)) " +  // Đếm số lượng người theo dõi u
            "FROM User u " +
            "LEFT JOIN UserFollow uf ON uf.userFollow.id = u.id AND uf.user.id = :currentUserId " +
            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<SearchUserDTO> searchUsersWithFollowStatus(@Param("searchTerm") String searchTerm, @Param("currentUserId") Long currentUserId);


}