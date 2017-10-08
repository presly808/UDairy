package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import ua.artcode.udiary.controller.MainController;

public class LoginHandler {


    private MainController mainController;

    public LoginHandler(MainController mainController) {
        this.mainController = mainController;
    }

    public void handle(HttpExchange httpExchange){

    }
}
