package com.levelupseon.demo3.mapper;

import com.levelupseon.demo3.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTest {
  @Autowired
  MemberMapper memberMapper;

  @Test
  public void testExist() {
    Assertions.assertNotNull(memberMapper);
    log.info("{}", memberMapper);
  }

  @Test
  public void testSelectOne() {
    Member member = memberMapper.findById("qwe");
    log.info("{}", member);
  }
}
