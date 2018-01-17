package com.alibaba.socket.demo04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Dali on 2017/12/12.
 *  创建DatagramSocke,绑定端口号
 *  创建数组,用于接收
 *  创建DatagramPacke
 *  调用DatagramSocket的receive方法
 *  拆包:
 *      送的IP地址;
 *      接受到的字节个数
 *      发送端的端口号
 */
public class UDPReceive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6000);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,data.length);
        socket.receive(packet);

        //获取发送端ip和端口号
        InetAddress inet = packet.getAddress();
        String address = inet.getHostAddress();
        String hostName = inet.getHostName();
        System.out.println(address + "---" + hostName);
        int port = packet.getPort();
        System.out.println("port is " + port);
        //拆包
        int length = packet.getLength();
        System.out.println(new String(data,0,length));
        socket.close();
    }
}
