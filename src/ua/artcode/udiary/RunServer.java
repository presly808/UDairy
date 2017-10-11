package ua.artcode.udiary;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.controller.MainControllerImpl;
import ua.artcode.udiary.dao.AppDataContainer;
import ua.artcode.udiary.dao.RecordDaoImpl;
import ua.artcode.udiary.dao.RecordDaoJsonImpl;
import ua.artcode.udiary.rest.*;
import ua.artcode.udiary.dao.UserDaoJsonImpl;
import ua.artcode.udiary.rest.AddRecordHandler;
import ua.artcode.udiary.rest.GetRecordHandler;
import ua.artcode.udiary.rest.HelloHandler;

import java.net.InetSocketAddress;

/**
 * Created by serhii on 07.10.17.
 */
public class RunServer {

    private static final String DATA_PATH = "./src/ua/artcode/udiary/resources/data.txt";

    public static void main(String[] args) throws Exception {
        MainController mainController = new MainControllerImpl(
                                            new UserDaoJsonImpl(DATA_PATH));


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

    /*static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder builder = new StringBuilder();

            builder.append("<h1>URI: ").append(exchange.getRequestURI()).append("</h1>");

            Headers headers = exchange.getRequestHeaders();
            for (String header : headers.keySet()) {
                builder.append("<p>").append(header).append("=")
                        .append(headers.getFirst(header)).append("</p>");
            }

            byte[] bytes = builder.toString().getBytes();
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    static class Auth extends Authenticator {
        @Override
        public Result authenticate(HttpExchange httpExchange) {
            if ("/forbidden".equals(httpExchange.getRequestURI().toString()))
                return new Failure(403);
            else
                return new Success(new HttpPrincipal("c0nst", "realm"));
        }
    }*/
}
