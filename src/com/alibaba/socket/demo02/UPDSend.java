package com.alibaba.socket.demo02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by Dali on 2017/12/13.
 */
public class UPDSend {
    public static void main(String[] args) throws IOException {
    InetAddress inet = InetAddress.getByName("127.0.0.1");
    DatagramSocket socket = new DatagramSocket();
    Scanner sc = new Scanner(System.in);
    while(true){
        String message = sc.nextLine();
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data,data.length,inet,6000);
        socket.send(packet);
    }
//    socket.close();
    }


}
