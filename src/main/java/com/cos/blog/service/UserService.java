package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
@RequiredArgsConstructor //생성자를 쓰지 않아도 자동으로 생성자를 생성해준다.
public class UserService {

    //@Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Autowired
//    private UserRepository userRepository;

    @Transactional
    public void join(User user) {

        String rawPassword = user.getPassword(); // 원래 비밀번호
        String encPassword = encoder.encode(rawPassword); // 해쉬 비밀번호
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


//전통적인 로그인 방식
//    @Transactional(readOnly = true) //select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
