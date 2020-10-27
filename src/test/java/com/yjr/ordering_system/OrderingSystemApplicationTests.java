package com.yjr.ordering_system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class OrderingSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        log.debug("debug.....");
        log.info("info.......");
        log.error("error.....");
    }

}
