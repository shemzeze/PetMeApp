//package com.example.petmeapp;
//
//
//import android.util.Log;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.URL;
//
//public class Upload extends Thread {
//
//    public static final int SERVERPORT = 8901;
//
//    public static void main() {
//
//        try {
//            System.out.println("S: Connecting...");
//
//            ServerSocket serverSocket = new ServerSocket(SERVERPORT);
//            System.out.println("S: Socket Established...");
//
//            Socket client = serverSocket.accept();
//            System.out.println("S: Receiving...");
//
//            ObjectOutputStream put = new ObjectOutputStream(
//                    client.getOutputStream());
//
////            String s = "adios.wav";
////            String str = "C:/";
////            String path = str + s;
////            System.out.println("The requested file is path: " + path);
////            System.out.println("The requested file is : " + s);
//            File f = new File();
//            if (f.isFile()) {
//                FileInputStream fis = new FileInputStream(f);
//
//                byte[] buf = new byte[1024];
//                int read;
//                while ((read = fis.read(buf, 0, 1024)) != -1) {
//                    put.write(buf, 0, read);
//                    put.flush();
//                }
//
//                System.out.println("File transfered");
//                client.close();
//                serverSocket.close();
//                fis.close();
//            }
//
//        } catch (Exception e) {
//            System.out.println("S: Error");
//            e.printStackTrace();
//        } finally {
//
//        }
//    }
//
//
////    public static final String UPLOAD_URL= "C:\\Users\\Shem\\Desktop\\PetME\\Server Uploads/server2.php";
////
////    private int serverResponseCode;
////
////    public String uploadVideo(String file) {
////
////        String fileName = file;
////        HttpURLConnection conn = null;
////        DataOutputStream dos = null;
////        String lineEnd = "\r\n";
////        String twoHyphens = "--";
////        String boundary = "*****";
////        int bytesRead, bytesAvailable, bufferSize;
////        byte[] buffer;
////        int maxBufferSize = 1 * 1024 * 1024;
////
////        File sourceFile = new File(file);
////        if (!sourceFile.isFile()) {
////            Log.e("Huzza", "Source File Does not exist");
////            return null;
////        }
////
////        try {
////            FileInputStream fileInputStream = new FileInputStream(sourceFile);
////            URL url = new URL(UPLOAD_URL);
////            conn = (HttpURLConnection) url.openConnection();
////            conn.setDoInput(true);
////            conn.setDoOutput(true);
////            conn.setUseCaches(false);
////            conn.setRequestMethod("POST");
////            conn.setRequestProperty("Connection", "Keep-Alive");
////            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
////            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
////            conn.setRequestProperty("myFile", fileName);
////            dos = new DataOutputStream(conn.getOutputStream());
////
////            dos.writeBytes(twoHyphens + boundary + lineEnd);
////            dos.writeBytes("Content-Disposition: form-data; name=\"myFile\";filename=\"" + fileName + "\"" + lineEnd);
////            dos.writeBytes(lineEnd);
////
////            bytesAvailable = fileInputStream.available();
////            Log.i("Huzza", "Initial .available : " + bytesAvailable);
////
////            bufferSize = Math.min(bytesAvailable, maxBufferSize);
////            buffer = new byte[bufferSize];
////
////            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
////
////            while (bytesRead > 0) {
////                dos.write(buffer, 0, bufferSize);
////                bytesAvailable = fileInputStream.available();
////                bufferSize = Math.min(bytesAvailable, maxBufferSize);
////                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
////            }
////
////            dos.writeBytes(lineEnd);
////            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
////
////            serverResponseCode = conn.getResponseCode();
////
////            fileInputStream.close();
////            dos.flush();
////            dos.close();
////        } catch (MalformedURLException ex) {
////            ex.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        if (serverResponseCode == 200) {
////            StringBuilder sb = new StringBuilder();
////            try {
////                BufferedReader rd = new BufferedReader(new InputStreamReader(conn
////                        .getInputStream()));
////                String line;
////                while ((line = rd.readLine()) != null) {
////                    sb.append(line);
////                }
////                rd.close();
////            } catch (IOException ioex) {
////            }
////            return sb.toString();
////        }else {
////            return "Could not upload";
////        }
////    }
//
//}
