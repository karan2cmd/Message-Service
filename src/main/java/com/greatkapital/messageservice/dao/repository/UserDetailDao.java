package com.greatkapital.messageservice.dao.repository;

import com.greatkapital.messageservice.model.Message;
import com.greatkapital.messageservice.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailDao extends JpaRepository<UserDto, String> {
}
