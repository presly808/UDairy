package ua.artcode.udiary.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.utils.JsonUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by serhii on 07.10.17.
 */
public class GetRecordHandler implements HttpHandler {


    private MainController mainController;

    public GetRecordHandler(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        try {
            String allQueryParams = httpExchange.getRequestURI().getQuery();


            String id = allQueryParams.split("=")[1];
            // prepare response (model -> json)
            // send response to a client
            //send success message
            Record loaded = mainController.getRecordById(id);

            String savedRecordJson = JsonUtils.toJson(loaded);

            byte[] bytes = savedRecordJson.getBytes();
            httpExchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (AppException e) {
            // send error message
            e.printStackTrace();
        }
    }
}
