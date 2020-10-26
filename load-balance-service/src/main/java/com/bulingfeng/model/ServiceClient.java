package com.bulingfeng.model;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-26
 */
public class ServiceClient {
    private String host;
    private int port;

    public ServiceClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ServiceClient() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServiceClient{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
