package domain.usecases.utils;

import java.time.LocalDate;
import java.util.Collection;

public abstract class Validator<T> {
    public abstract Notification validate(T type);

    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    } //para Strings

    public static boolean nullOrEmpty(Collection collection){
        return collection == null || collection.isEmpty();
    } //sobrecarga para collections

    public static boolean nullOrEmpty(LocalDate localDate){
        return localDate == null;
    }//sobrecarga para campo date

}
