package ua.artcode.udiary;

import com.sun.net.httpserver.HttpServer;
import ua.artcode.udiary.config.ConfigHolder;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.controller.MainControllerImpl;
import ua.artcode.udiary.dao.UserDaoJsonImpl;
import ua.artcode.udiary.rest.*;

import java.io.File;
import java.net.InetSocketAddress;

/**
 * Created by serhii on 07.10.17.
 */
public class RunServer {

    // todo use yaml config file
    private static final String CONFIG_FILE_PATH = "/app.properties";

    public static void main(String[] args) throws Exception {

        ConfigHolder ch = new ConfigHolder(
                new File(RunServer.class.getResource(CONFIG_FILE_PATH).getFile()).getAbsolutePath());

        File file = new File(RunServer.class.getResource(ch.getProperty("app.db.path")).getFile());

        MainController mainController = new MainControllerImpl(
                new UserDaoJsonImpl(file.getAbsolutePath()));


        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(Integer.parseInt(ch.getProperty("app.port"))), 0);


        server.createContext("/", new HelloHandler());

        server.createContext("/login", new LoginHandler(mainController));
        server.createContext("/register", new RegisterHandler(mainController));

        server.createContext("/add-record", new AddRecordHandler(mainController));
        server.createContext("/get-record", new GetRecordHandler(mainController));

        File staticFolder =
                new File(RunServer.class.getResource(ch.getProperty("app.web.static")).getFile());
        server.createContext("/view", new StaticFilesHandler(staticFolder.getAbsolutePath()));


        server.setExecutor(command -> {
            try{
                command.run();
            }catch (Throwable ex){
                ex.printStackTrace();
            }
        });
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
