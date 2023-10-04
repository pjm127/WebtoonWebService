package com.pknuwws.wws.user.infrastructure;

import com.pknuwws.wws.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserId(String userId);
    Optional<User> findByNickName(String nickName);



}
