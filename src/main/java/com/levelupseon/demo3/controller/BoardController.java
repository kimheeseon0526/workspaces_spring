package com.levelupseon.demo3.controller;

import com.levelupseon.demo3.domain.Board;
import com.levelupseon.demo3.domain.Member;
import com.levelupseon.demo3.domain.dto.Criteria;
import com.levelupseon.demo3.domain.dto.PageDto;
import com.levelupseon.demo3.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("board")
@AllArgsConstructor
@Slf4j
public class BoardController {
  private BoardService boardService;

  @GetMapping("list")
  public void list(Criteria criteria, Model model) {
    model.addAttribute("boards", boardService.list(criteria));
    model.addAttribute("pageDto", new PageDto(criteria, boardService.getCount(criteria)));
  }

  @GetMapping({"view", "modify"}) //수정도 조회 후에 수정하니까
  public void view(Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
    model.addAttribute("board",boardService.findBy(bno));
    //http://localhost:8080/board/list?cno=2&amount=10&type=&keyword=&page1
    //view -> list로 갈때 list 아래의 cri가 남아있어야됨
  }
  @GetMapping("write")
  public String write(@ModelAttribute("cri") Criteria cri, @SessionAttribute(value = "member", required = false) Member member) {
    if(member == null){ //비로그인상태 처리
      return "redirect/member/login"; //현 위치 write에서 list로
    }
    return "board/write";
  }
  //ModelAttribute 쓰면 cri의 모든 정보를 가져옴
  //SessionAttribute : null이면 비로그인 , 아니면 member 정보 있음
  @PostMapping("write")
  public String write(Board board, Criteria cri) {
    log.info("board : {}", board);
    boardService.write(board);
    return "redirect:/board/list?" + cri.getQs2();
  }
  @PostMapping("modify")
  public String modify(Board board, Criteria cri) {
    log.info("board : {}", board);
    boardService.modify(board);
    return "redirect:/board/list?" + cri.getQs2();
  }
  @RequestMapping("remove")
  public String remove(Long bno, Criteria cri) {
    boardService.remove(bno);
    return "redirect:/board/list?" + cri.getQs2();
  }
}
