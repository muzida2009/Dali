package com.alibaba.socket.demo02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Dali on 2017/12/13.
 */
public class UPDReceive {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);
        byte[] data = new byte[1024];
        while(true){
        DatagramPacket packet = new DatagramPacket(data,data.length);
        socket.receive(packet);
        int length = packet.getLength();
        System.out.println(new String(data,0,length));
        }
    }
}
