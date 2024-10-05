package org.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpMemoryLeakExample implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println("\n\nConn " + i);
            HttpMemoryLeakExample t = new HttpMemoryLeakExample();
            t.run();
        }
    }

    @Override
    public void run() {
        HttpURLConnection connection;
        BufferedReader reader;

        try {
            URL url = URI.create("https://www.google.com").toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            //while ((line = reader.readLine()) != null) {
                System.out.print(connection.getResponseCode() + " - " + connection.getResponseMessage());
            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makeHttpCall() throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = URI.create("https://www.google.com").toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}