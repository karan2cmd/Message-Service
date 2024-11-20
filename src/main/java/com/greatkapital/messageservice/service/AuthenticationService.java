package com.greatkapital.messageservice.service;

import com.greatkapital.messageservice.config.EncrypctionServiceConfiguration;
import com.greatkapital.messageservice.dao.adaptor.MessageDetailsDBAdaptor;
import com.greatkapital.messageservice.dao.repository.UserDetailDao;
import com.greatkapital.messageservice.dto.SignupDto;
import com.greatkapital.messageservice.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {


    @Autowired
    private UserDetailDao userDetailDao;


    public ResponseEntity<?> signUp(SignupDto request) {
        Optional<UserDto> res = userDetailDao.findById(request.getUserName());
        if(res.isPresent()){
                return error///
            }

        UserDto u = UserDto.builder().userName("gnjkdf").password("dgfhg").build();
        userDetailDao.save(u);
        }



    public ResponseEntity<?> login(SignupDto request) {
        Optional<UserDto> res = userDetailDao.findById(request.getUserName());
        if(res.isPresent()){
            res.get().getPassword()==request.getPassward(){
                return res//
            }
        }
        UserDto u = UserDto.builder().userName("gnjkdf").password("dgfhg").build();
        userDetailDao.save(u);

    }
}
