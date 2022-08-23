package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
@RequiredArgsConstructor //생성자를 쓰지 않아도 자동으로 생성자를 생성해준다.
public class BoardService {

    //@Autowired
    private final BoardRepository boardRepository;


    @Transactional
    public void write(Board board) {

        board.setCount(0);
        boardRepository.save(board);

    }

}
