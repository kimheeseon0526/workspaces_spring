package com.levelupseon.demo3.controller;

import com.levelupseon.demo3.domain.Member;
import com.levelupseon.demo3.service.MemberService;
import com.levelupseon.demo3.util.AlertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
@AllArgsConstructor
@Slf4j
public class MemberController {
  private MemberService memberService;

  @GetMapping("login")
  public void loginForm() {}

  @GetMapping("/register")
  public void registerForm() {}

  @PostMapping("login")
  public String login(Member member, HttpSession session, RedirectAttributes redirectAttributes) {
    log.info("{}", member);
    if(memberService.login(member.getId(), member.getPw())) {
      session.setAttribute("member", memberService.findById(member.getId()));
      return "redirect:/";
    }
    redirectAttributes.addFlashAttribute("msg", "로그인 실패");
    return "redirect:/member/login";
    //로그인 성공시 루트로 이동 -> sendRedirect 부분
  }
  @PostMapping("register")
  public String register(Member member) {
    memberService.register(member);
    return "redirect:/";
  }

  @RequestMapping(value= "logout", method = {RequestMethod.GET, RequestMethod.POST})
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}
