package com.cos.blog.test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(html파일)
//@Controller

//사용자가 요청-> 응답(Data)
@Slf4j
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();

        log.info("{} getter : {}", TAG, m.getUsername());
//        System.out.println(TAG + "getter : " + m.getUsername());
        m.setUsername("cos");
        log.info("{} getter : {}", TAG, m.getUsername());
//        System.out.println(TAG + "getter : " + m.getUsername());

        return "lombok test 완료";
    }


    //http://localhost:8000/blog/http/get?id=1&username=ssar&password=1234&email=ssar@nate.com
    //인터넷 브라우저 요청은 get만 된다.
    //http://localhost:8000/blog/http/get
    //select
    @GetMapping("/http/get")
    public String getTest(Member member) {

        //String.format을 이용하여 만듦
        return String.format("Get 요청: %d, %s, %s, %s", member.getId(), member.getUsername(), member.getPassword(), member.getEmail());
//        return "get요청: "+member.getId()+","+member.getUsername()+","+member.getPassword()+","+member.getEmail();
    }

    //return type을 ResponseEntity로 만듦
//    @GetMapping("/http/get")
//    public ResponseEntity getTest(Member member) {
//
//        return new ResponseEntity(HttpStatus.OK);
//    }

    //MessageConverter(스프링부트)
    //appllication/json
    //insert
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member member) {


        return String.format("post요청: %d, %s, %s, %s", member.getId(), member.getUsername(), member.getPassword(), member.getEmail());
    }

//    @PostMapping("/http/post")//text/plan
//    public String postTest(@RequestBody String text) {
//
//        return "post요청: "+text;
//    }

    //update
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member member) {

        return "put요청: " + member.getId() + "," + member.getUsername() + "," + member.getPassword() + "," + member.getEmail();
    }

    //delete
    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody Member member) {

        return "delete요청: " + member.getId() + "," + member.getUsername() + "," + member.getPassword() + "," + member.getEmail();
    }
}
