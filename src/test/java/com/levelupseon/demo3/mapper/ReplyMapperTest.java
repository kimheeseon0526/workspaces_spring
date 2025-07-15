package com.levelupseon.demo3.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyMapperTest {
    @Autowired
    ReplyMapper replyMapper;

    @Test
    public void testExist() {
        Assertions.assertNotNull(replyMapper);
        log.info("{}", replyMapper);
    }
}
