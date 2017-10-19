package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.MessageType;
import ua.artcode.udiary.model.ResponseMessage;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.HttpUtils;
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

        if(httpExchange.getRequestMethod().equals("POST")){
            ResponseMessage notification = new ResponseMessage(MessageType.successful,
                    "Congratulations, your registration has passed successfully") ;
            User user = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), User.class);
            try {
                // todo add register method to MainController
                User savedUser = mainController.addUser(user);
            } catch (AppException e) {
                e.printStackTrace();

                notification = new ResponseMessage(MessageType.error,
                        "user with such email already exists, or don`t validate email/pass");
            }

            String responseNotification = JsonUtils.toJson(notification);
            HttpUtils.sendResponse(httpExchange, responseNotification, 200);
            //TODO redirects to the /login page
        } else {
            HttpUtils.sendResponse(httpExchange, "Method is not supported", 405);
        }


    }
}
