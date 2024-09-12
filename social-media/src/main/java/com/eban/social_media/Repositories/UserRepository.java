package com.eban.social_media.Repositories;

import com.eban.social_media.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Các phương thức CRUD có sẵn từ JpaRepository
    User findByEmail(String email);
    User findByUsername(String username);

    // Kiểm tra sự tồn tại của user theo email
    boolean existsByEmail(String email);

    // Kiểm tra sự tồn tại của user theo username
    boolean existsByUsername(String username);

}