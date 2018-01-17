package com.alibaba.socket.demo03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Dali on 2017/12/14.
 */
public class Upload implements Runnable {
    private Socket socket;
    public Upload (Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            String fileName = "Girl" + "_" + System.currentTimeMillis() + ".jpg";
            File file = new File("d:" + File.separator + "ZDownload" + File.separator + "storage");
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file + File.separator + fileName);

            byte[] data = new byte[1024];
            int len = 0;
            while ((len = in.read(data)) != -1) {
                fos.write(data,0,len);
            }

            socket.getOutputStream().write("Success".getBytes());

            fos.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
