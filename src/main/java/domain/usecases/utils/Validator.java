package domain.usecases.utils;

import java.math.BigDecimal;
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

    public static boolean isNull(Enum enumeracao){
        return enumeracao == null;
    }

    public static boolean isNull(LocalDate localDate){
        return localDate == null;
    }//sobrecarga para campo date

    public static boolean isNull(Boolean bool){
        return bool == null;
    }

    public static boolean isNullOrLesserEqual(Integer integer){
        return integer == null || integer <= 0;
    }

    public static boolean isNullOrLesserEqual(Float floatNumber){
        return floatNumber == null || floatNumber <= 0;
    }

    public static boolean isNullOrLesserEqual(BigDecimal bigDecimal){
        return bigDecimal == null || bigDecimal.compareTo(BigDecimal.valueOf(0)) <= 0;
    }
}
