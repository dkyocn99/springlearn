package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//suto-increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨

//    @ColumnDefault("0")
    private int count;//조회수

    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, one = User
    @JoinColumn(name = "userId")//필드값 userId로 들어감
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) //mappedBy 연관관계의 주인이 아니다(난 FK가 아니예요) DB에 칼럼을 만들지 마세요
    @JsonIgnoreProperties({"board"}) //무한참조 방지를 위해 설정
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;
}
