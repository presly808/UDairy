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


    // TODO send message {"messageType":"success", "message" : "text"}
    // or {"messageType" : "error", "message" : "error"}
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String notification = "Congratulations, your registration has passed successfully";
        User user = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), User.class);
        try {
            // todo add register method to MainController
            User savedUser = mainController.addUser(user);
            //String savedUserJson = JsonUtils.toJson(savedUser);
            //byte[] bytes = savedUserJson.getBytes();
        } catch (AppException e) {
            e.printStackTrace();
            notification = "user with such email already exists, or don`t validate email/pass";
        }
        String responceNotification = JsonUtils.toJson(notification);
        byte[] bytes = responceNotification.getBytes();
        httpExchange.sendResponseHeaders(200, bytes.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();
        //TODO redirects to the /login page
    }
}
