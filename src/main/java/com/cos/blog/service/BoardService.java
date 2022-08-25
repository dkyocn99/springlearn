package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void write(Board board, User user) {

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }
    @Transactional(readOnly = true)
    public Page<Board> writeList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Board viewDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
    }
    @Transactional
    public void writeDelete(int id) {
        log.info("글삭제하기:",id);
        boardRepository.deleteById(id);
    }

    public void modifyWrite(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다."));

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent()); //해당 함수로 종료시(Service 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
    }
}
