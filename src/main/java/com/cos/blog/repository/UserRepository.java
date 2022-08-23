package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//DAO
//자동으로 bean등록이 된다.
//@Repository 생략 가능하다
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

}
//전통적인 로그인 방식
// JPA Naming 쿼리
//select * from user where username = ?1 and password = ?2
//    User findByUsernameAndPassword(String username, String password);

//    @Query(vlaue ="select * from user where username = ?1 and password=?2",nativeQuery = true)
//    User login(String username,String pssword);