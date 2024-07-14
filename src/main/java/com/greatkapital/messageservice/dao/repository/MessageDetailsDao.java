package com.greatkapital.messageservice.dao.repository;

import com.greatkapital.messageservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDetailsDao extends JpaRepository<Message, String> {
}
