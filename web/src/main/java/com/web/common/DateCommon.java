package com.web.common;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by duyle on 21/02/2017.
 */
public class DateCommon {

    public Date getDateNow() {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = df.format(calendar.getTime());
        return Date.valueOf(stringDate);
    }

    public java.util.Date convertFromSQLDateToJAVADate(
            java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    public Date convertStringToDateSql(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date result = formatter.parse(date);
            return new Date(result.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
