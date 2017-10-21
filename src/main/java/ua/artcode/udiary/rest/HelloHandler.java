package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by serhii on 07.10.17.
 */
// todo remove
public class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String helloMessage = "Hello";

        byte[] bytes = helloMessage.getBytes();
        httpExchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
