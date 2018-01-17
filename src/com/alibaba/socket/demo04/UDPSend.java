package com.alibaba.socket.demo04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *  1. DataGramSocket: 码头
 *  2. DataGramPacket:集装箱
 */
public class UDPSend {
    public static void main(String[] args) throws Exception {
        byte[] date = "你好UDP".getBytes();
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        //1. 创建packet
        DatagramPacket packet = new DatagramPacket(date,date.length,inet,6000);
        //2. create socket
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        socket.close();
    }
}
