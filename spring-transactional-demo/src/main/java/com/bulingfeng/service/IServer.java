package com.bulingfeng.service;

import java.io.IOException;

public interface IServer {
    void methodA();

    void methodB();

    void checkExceptionMethod() throws IOException;

    void required();
    void requiredNew();
}
