package com.levelupseon.demo3.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberServiceTest {
  @Autowired
  private MemberService memberService;

  @Test
  public void testExist() {
    log.info("{}", memberService);
  }

  @Test
  public void testLogin() {
    memberService.login("qwe", "qwe");
  }
}
