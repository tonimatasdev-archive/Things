package dev.tonimatas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class DownloadFiles {
    public static final String fileURL = "https://cdimage.ubuntu.com/kubuntu/releases/24.04/release/kubuntu-24.04-desktop-amd64.iso";
    private static final DecimalFormat oneDecimalFormat = new DecimalFormat("#.#");
    private static final long everyMillis = 1000;
    private static boolean finished = false;

    public static void main(String[] args) {
        try {
            URL url = URI.create(fileURL).toURL();
            URLConnection connection = url.openConnection();
            connection.connect();

            File dir = new File("downloads");
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    System.out.println("Directory created.");
                } else {
                    System.out.println("Directory not created.");
                }
            }

            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            String saveFilePath = "downloads" + File.separator + fileName;
            File resultFile = new File(saveFilePath);

            new Thread(() -> {
                double oldFileSizeInMB = 0;
                int duration = 0;
                
                System.out.println("Downloading " + fileName + " to " + saveFilePath);
                
                while (!finished) {
                    try {
                        //noinspection BusyWait
                        Thread.sleep(everyMillis);

                        double fileSizeInMB = (double) resultFile.length() / 1048576;
                        double diff = fileSizeInMB - oldFileSizeInMB;
                        oldFileSizeInMB = fileSizeInMB;
                        
                        System.out.print("\r" + oneDecimalFormat.format(diff) + " MB/S | Total: " + oneDecimalFormat.format(fileSizeInMB) + " MB | " + duration + " seconds");
                        
                        duration++;
                    } catch (InterruptedException e) {
                        System.out.println("Error on calculate download status.");
                    }
                }
            }).start();

            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {
                
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                finished = true;
                double fileSizeInMB = (double) resultFile.length() / 1048576;

                System.out.println("File downloaded to: " + saveFilePath + " | " + oneDecimalFormat.format(fileSizeInMB) + " MB");
            }
        } catch (IOException e) {
            System.out.println("Error on download the file.");
        }
    }
}
