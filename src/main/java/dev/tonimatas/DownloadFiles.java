package dev.tonimatas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFiles {
    public static final String fileURL = "https://piston-data.mojang.com/v1/objects/0b4dba049482496c507b2387a73a913230ebbd76/server.txt";

    public static void main(String[] args) {
        try {
            URL url = new URL(fileURL);
            URLConnection connection = url.openConnection();
            connection.connect();

            File dir = new File("downloads");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            String saveFilePath = "downloads" + File.separator + fileName;

            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File downloaded to: " + saveFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
