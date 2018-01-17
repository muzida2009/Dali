package com.alibaba.socket.demo03;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 *  1. Socket连接服务器
 *  2. 通过Socket获取字节输出流
 *  3.使用流对象, 读取图片
 *  4.获取字节输出流,将图片写到服务器,采用缓冲流
 * Created by Dali on 2017/12/14.
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet,8000);
        OutputStream out = socket.getOutputStream();

        //读取图片
        FileInputStream fis = new FileInputStream("c:"+ File.separator + "opt" + File.separator+"upload"+File.separator+"33.jpg");
        byte[] data = new byte[1024];
        int len = 0;
        while ((len = fis.read(data)) != -1){
            out.write(data,0,len);
        }
        socket.shutdownOutput();

        //读取服务器提示
        InputStream in = socket.getInputStream();
        len = in.read(data);
        System.out.println(new String(data,0,len));
        fis.close();
        socket.close();

    }
}
