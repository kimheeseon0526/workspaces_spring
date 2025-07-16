package com.levelupseon.demo3.controller;

import com.levelupseon.demo3.domain.Member;
import com.levelupseon.demo3.domain.Reply;
import com.levelupseon.demo3.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reply")
@AllArgsConstructor
public class ReplyController {
  @GetMapping("test1")
  public String test1() {
    return "hello world!";
  }
  @GetMapping("test2")
  public Member test2() {
    return Member.builder().build();
    //json 결과값 출력됨
  }
  private ReplyService replyService;

  //단일조회
  @GetMapping("{rno}")  //520
  public Reply get(@PathVariable Long rno) {
    return replyService.findBy(rno);
  }
  @GetMapping({"list/{bno}", "list/{bno}/{lastRno}"}) //4092-520
  public List<Reply> list(@PathVariable Long bno, @PathVariable(required = false) Long lastRno) {
    return replyService.list(bno, lastRno);
  }

  @PostMapping("/")
  public Map<String, Object> write(@RequestBody  Reply reply) {
    replyService.register(reply);
    return Map.of("result", true, "reply", reply);
  }


  @PutMapping("{rno}")
  public Map<String, Object> modify(@RequestBody Reply reply, @PathVariable Long rno) {
    replyService.modify(reply);
    return Map.of("result", true, "reply", reply);
  }

  @DeleteMapping("{rno}")
  public ResponseEntity<Map<String, Object>> remove(@PathVariable Long rno) {
    //PathVariable : URL 경로에 있는 rno 값 받아오기
    replyService.remove(rno);
    return ResponseEntity.ok().body(Map.of("result", true));
  }
}
