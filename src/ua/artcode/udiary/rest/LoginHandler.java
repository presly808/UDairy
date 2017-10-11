package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.JsonUtils;

import java.io.IOException;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler{


    private MainController mainController;

    public LoginHandler(MainController mainController)  {
        this.mainController = mainController;
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        User checkUser = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), User.class);
        User savedUser = null;
        try {
            savedUser = mainController.logInUser(checkUser);
        } catch (AppException e) {
            e.printStackTrace();
        }
             String savedUserJson = JsonUtils.toJson(savedUser);
             byte[] bytes = savedUserJson.getBytes();
//           httpExchange.setAttribute();
        //TODO implementation of cookie (httpExchange.getResponseHeaders().add("Set-Cookies", formattedCookiesString -> (https://developer.mozilla.org/en-US/docs/Web/HTTP/Cookies)))
            // httpExchange.getResponseHeaders().add("Set-Cookie", "AccessToken=31d4d96e407aad42; Path=/; Secure; HttpOnly");
            // httpExchange.getResponseHeaders().add("Set-Cookie", "email=Ivanpiontkevich@gmail.com; Path=/login; Secure; HttpOnly");
             httpExchange.getResponseHeaders().add("cache-control", "no-cache");
             httpExchange.sendResponseHeaders(200, bytes.length);
             OutputStream os = httpExchange.getResponseBody();
             os.write(bytes);
             os.close();

    }


}
