package cinema.funzioni;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class FunzionalitaDate {
    public static boolean stessoGiorno(Date data1, Date data2) {
        LocalDate localDate1 = convertToLocalDateViaInstant(data1);
        LocalDate localDate2 = convertToLocalDateViaInstant(data2);

        if (localDate1.getDayOfYear() == localDate2.getDayOfYear() && localDate1.getYear() == localDate2.getYear()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean stessaData(Date data1, Date data2) {
        Instant in1 = data1.toInstant();
        Instant in2 = data2.toInstant();
        if (in1.equals(in2)) {
            return true;
        }
        return false;
    }

    public static boolean dateSuccesive(Date data1, Date data2) {
        Instant in1 = data1.toInstant();
        Instant in2 = data2.toInstant();
        if (in1.isBefore(in2)) {
            return true;
        }
        return false;
    }

    private static LocalDate convertToLocalDateViaInstant(Date data) {
        return data.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date giornoDopo(Date data) {
        return Date.from(Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    public static Date modificaOrario(Date data, String orario) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        String[] HH_MM_SS = orario.split(":");
        calendario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HH_MM_SS[0]));
        calendario.set(Calendar.MINUTE, Integer.parseInt(HH_MM_SS[1]));
        calendario.set(Calendar.SECOND, Integer.parseInt(HH_MM_SS[2]));
        try {
            return ValidatoreCampi.DATEFORMAT.parse(ValidatoreCampi.DATEFORMAT.format(calendario.getTime()));
        } catch (ParseException e) {
            return null;
        }
    }

    public static dateLabelFormatter generaDateLabelFormatter() {
        return new dateLabelFormatter();
    }

}

class dateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
