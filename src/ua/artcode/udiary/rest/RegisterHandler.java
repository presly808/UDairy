package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.JsonUtils;

import java.io.IOException;
import java.io.OutputStream;

public class RegisterHandler implements HttpHandler {

    private MainController mainController;

    public RegisterHandler(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        User user = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), User.class);
        try {
            User savedUser = mainController.registerUser(user);
            String savedUserJson = JsonUtils.toJson(savedUser);
            byte[] bytes = savedUserJson.getBytes();
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (AppException e) {
            e.printStackTrace();
        }
    }
}
