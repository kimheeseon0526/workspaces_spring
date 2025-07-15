package com.levelupseon.demo3.persistant;
//import com.levelupseon.demo3.mapper.TestMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
@Slf4j
public class PersistantTest {
    @Autowired
    private HikariConfig hikariConfig;
    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private TestMapper mapper;


    @Test
    public void testHikari(){
        log.info("hikari :: {} ", hikariConfig);
    }

    @Test
    public void testDataSource() {
        log.info("ds :: {} ", dataSource);
    }
//    @Test
//    public void testMapper() {
//        log.info("testMapper :: {} " , mapper);
//        log.info(mapper.now());
//    }
}
