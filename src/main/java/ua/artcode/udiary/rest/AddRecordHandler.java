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
public class AddRecordHandler implements HttpHandler {

    private MainController mainController;

    public AddRecordHandler(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        // get input json from httpExchange
//        String jsonBody = JsonUtils.jsonToString(httpExchange.getRequestBody());
        // json -> object(model)
        Record record = JsonUtils.jsonStreamToObj(httpExchange.getRequestBody(), Record.class);

        // call Controller (saveRecord)
        try {
            // prepare response (model -> json)
            // send response to a client
            //send success message
            Record saved = mainController.addRecord(record);

            if (saved != null){
                System.out.println("Record '" + record.getTitle() + "' has been saved.");
            }

            String savedRecordJson = JsonUtils.toJson(saved);

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
