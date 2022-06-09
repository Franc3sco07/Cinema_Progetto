package progetto.functions;

import java.util.Date;
import java.time.*;

public class ConfrontaDate {
    public static boolean stessoGiorno(Date data1, Date data2){
        LocalDate localDate1 = convertToLocalDateViaInstant(data1);
        LocalDate localDate2 = convertToLocalDateViaInstant(data2);

        if(localDate1.getDayOfYear() == localDate2.getDayOfYear() && localDate1.getYear() == localDate2.getYear()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean dateSuccesive (Date data1, Date data2){
        Instant in1 = data1.toInstant();
        Instant in2 = data2.toInstant();


        if(in1.isBefore(in2) ){
            return true;

        }
        return false;
    }

    private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
