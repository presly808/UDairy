package ua.artcode.udiary.utils;

import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

public class Validator {

    public static void validateRecord(Record record) throws AppException {

        if(record == null){
            throw new AppException("empty record");
        }

        if(record.getBody().isEmpty() || record.getTitle().isEmpty()){
            throw new AppException("empty body or title");
        }

         /*
         additional validation...
         */
    }

    public static void validateDairy(Dairy dairy) throws AppException {
        if(dairy == null){
            throw new AppException("empty dairy");
        }

         /*
         additional validation...
         */
    }

    public static void validateUser(User user) throws AppException {
        if(user == null){
            throw new AppException("empty user");
        }

         /*
         additional validation...
         */
    }

}
