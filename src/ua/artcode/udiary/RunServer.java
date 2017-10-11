package ua.artcode.udiary;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.controller.MainControllerImpl;
import ua.artcode.udiary.dao.RecordDaoJsonImpl;
import ua.artcode.udiary.rest.*;

import java.net.InetSocketAddress;

/**
 * Created by serhii on 07.10.17.
 */
public class RunServer {

    public static void main(String[] args) throws Exception {
        MainController mainController = new MainControllerImpl(
                                            new RecordDaoJsonImpl());


        HttpServer server = HttpServer.create();

        server.bind(new InetSocketAddress(8000), 0);

        HttpContext context =
                server.createContext("/add-record", new AddRecordHandler(mainController));

        server.createContext("/", new HelloHandler());

        server.createContext("/get-record", new GetRecordHandler(mainController));

        server.createContext("/register", new RegisterHandler(mainController));
        server.createContext("/", new LoginHandler(mainController));


        server.setExecutor(null);
        server.start();

        System.out.println("Server has been started");
    }

}
