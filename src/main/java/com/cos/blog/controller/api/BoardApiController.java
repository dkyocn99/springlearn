package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {


    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board) {

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //java오브젝트를 json으로 변환해서 리턴(jackson)
    }

}
