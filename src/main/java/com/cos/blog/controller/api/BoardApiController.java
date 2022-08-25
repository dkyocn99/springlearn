package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {


     private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {

        boardService.write(board,principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //java오브젝트를 json으로 변환해서 리턴(jackson)
    }

    @DeleteMapping("api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.writeDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PutMapping("api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.modifyWrite(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
