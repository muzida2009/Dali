package com.alibaba.socket.demo01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dali on 2017/12/13.
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        byte[] data = new byte[1024];//接受数据
        int length = is.read(data);
        System.out.println(new String(data,0,length));
        System.out.println(socket);

        //服务器返回数据
        OutputStream out = socket.getOutputStream();
        out.write("收到".getBytes());
        socket.close();
        server.close();
    }
}
