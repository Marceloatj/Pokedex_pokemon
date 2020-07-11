package br.com.pokedex_poke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

    public static String ddmmyyhhmm = "dd/MM/yy HH:mm";
    public static String formatDateDisplay = "dd/MM/yyyy HH:mm:ss";
    public static String ddMMyyyy = "dd/MM/yyyy";

    public static String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static String yyyyMMdd = "yyyy-MM-dd";

    public static String hhmm = "HH:mm";

    public static Date formatStringToDate(String data, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try{
            return sdf.parse(data);
        }catch (ParseException e){
            return null;
        }
    }

    public static String dataEhoraToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String dataEhoraToString(Date date) {
        return new SimpleDateFormat(formatDateDisplay).format(date);
    }

    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static int comparaData(Date data1, Date data2 ) {
        String dataA, dataB;
        dataA = dateToString(data1,ddMMyyyy);
        dataB = dateToString(data2,ddMMyyyy);

        data1 = formatStringToDate(dataA,ddMMyyyy);
        data2 = formatStringToDate(dataB,ddMMyyyy);

        return data1.compareTo(data2);
    }

    public static int comparaDataEHora(String dataA, String dataB ) {

        Date data1 = formatStringToDate(dataA,formatDateDisplay);
        Date data2 = formatStringToDate(dataB,formatDateDisplay);

        return data1.compareTo(data2);
    }

    public static Date longToDate(long timestamp){

        Date data = new Date(timestamp);
//        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
//        df.setTimeZone (TimeZone.getTimeZone ("GMT"));

        return data;
    }



    }


