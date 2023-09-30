package com.pknuwws.wws.user.infrastructure;

import com.pknuwws.wws.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
