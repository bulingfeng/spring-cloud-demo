package com.bulingfeng;

import com.bulingfeng.service.IServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionCheckTests {

    @Autowired
    private IServer iServer;

    @Test
    public void testCheckException() throws IOException {
        iServer.checkExceptionMethod();
    }
}
