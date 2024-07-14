package com.greatkapital.messageservice.dao.adaptor;

import com.greatkapital.messageservice.model.Message;
import com.greatkapital.messageservice.dao.repository.MessageDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageDetailsDBAdaptor {
    @Autowired
    private MessageDetailsDao messageDetailsDao;

    public Message saveMessage(Message message) {
        return messageDetailsDao.save(message);
    }

    public Optional<Message> getMessage(String id) {
        return messageDetailsDao.findById(id);
    }
}

