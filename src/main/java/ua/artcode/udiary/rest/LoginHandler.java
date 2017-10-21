package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.FileUtils;
import ua.artcode.udiary.utils.HttpUtils;

import java.io.IOException;

// todo finish logic
public class LoginHandler implements HttpHandler {

    MainController mainController;

    public LoginHandler(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        User checkUser = FileUtils.jsonStreamToObj(httpExchange.getRequestBody(), User.class);

        try {
            User userForCheck = mainController.logInUser(checkUser);
            String savedUserJson = FileUtils.toJson(userForCheck.getId());
            //TODO implementation of cookie (httpExchange.getResponseHeaders().add("Set-Cookies", formattedCookiesString -> (https://developer.mozilla.org/en-US/docs/Web/HTTP/Cookies)))
           // httpExchange.getResponseHeaders().add("Set-Cookie", String.format("AccessToken=%l; Path=/; Secure; HttpOnly", 9450945));
            httpExchange.getResponseHeaders().add("cache-control", "no-cache");
            HttpUtils.sendResponse(httpExchange,savedUserJson, 200 );
        } catch (AppException e) {
            e.printStackTrace();
        }

    }
}
