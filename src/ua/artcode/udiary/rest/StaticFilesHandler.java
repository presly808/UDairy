package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.udiary.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by serhii on 15.10.17.
 */
public class StaticFilesHandler implements HttpHandler {

    private final File staticFilesFolder;

    public StaticFilesHandler(String staticFilesPath) {
        this.staticFilesFolder = new File(staticFilesPath);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().toString();

        File file = new File(staticFilesFolder.getAbsolutePath() + query);

        if(file.exists() && file.isFile()){
            String body = JsonUtils.readJsonFromFile(file.getAbsolutePath());
            sendResponse(httpExchange, body, 200);
        } else {
            sendResponse(httpExchange, "Static file not found", 404);
        }


    }

    private static void sendResponse(HttpExchange httpExchange, String body, int httpCode) throws IOException {
        try(OutputStream os = httpExchange.getResponseBody()){
            byte[] bytes = body.getBytes();
            httpExchange.sendResponseHeaders(httpCode, bytes.length);
            os.write(bytes);
        }
    }
}
