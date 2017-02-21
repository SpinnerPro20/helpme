package laba;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
        public static void main(String[] args)  {
            String query = "http://api.fixer.io/latest?base=USD&symbols=RUB";
            HttpURLConnection connection = null;//наше соединение
            Gson gson = new GsonBuilder()//регистрируем десериалайзер в гсоне
                    .registerTypeAdapter(RateObject.class, new RatesDeserializer())

                    .create();
            try {
                connection = (HttpURLConnection) new URL(query).openConnection();


                //заполняем свойства подключения
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setConnectTimeout(500);
                connection.setReadTimeout(500);



                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {//проверяем все ли ОК
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    gson.fromJson(in, ApiResponse.class);//распарсивание объектов
                } else {
                    System.out.println("fail: " + connection.getResponseMessage());

                }


            } catch (Throwable cause) {
                cause.printStackTrace();//при каких то ошибках выводим их в консоль
            } finally {//в любом случае закрываем соединение
                if (connection != null) {
                    connection.disconnect();
                }
            }

        }
    }


