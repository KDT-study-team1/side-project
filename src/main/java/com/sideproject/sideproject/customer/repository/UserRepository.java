package com.sideproject.sideproject.customer.repository;

import com.sideproject.sideproject.customer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
