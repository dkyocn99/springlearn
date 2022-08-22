package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM-> Java(다른언어)Object-> 테이블로 매핑해주는 기ㅔㅕ퓨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert Insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; //시퀸스, auto_increment

    @Column(nullable = false, length = 30)
    private String username; //아이디

    @Column(nullable = false, length = 100)//해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    //    @ColumnDefault("user")
//    private String role;//Enum을 쓰는게 좋다.=> admin, user, manager 셋 중에 하나만 들어가게 만들 수 있다.
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp//시간이 자동 입력
    private Timestamp createDate;

    //웬만하면 Setter는 사용하지 않고 아래와 같이 메서드를 만들어 Setter 대신에 사용한다.
//    public void changeEmail(String email) {
//        this.email = email;
//    }
}
