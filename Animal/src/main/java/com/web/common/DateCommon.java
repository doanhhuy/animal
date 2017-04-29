package com.web.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public Timestamp getTimestampNow() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    public java.util.Date convertFromSQLDateToJAVADate(
            java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    public Timestamp convertStringToTimestamp(String timestamp) {
        return Timestamp.valueOf(timestamp);
    }

    public Date convertStringToDateSql(String date) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateUtil = null;
            dateUtil = sdf1.parse(date);
            java.sql.Date sqlStartDate = new java.sql.Date(dateUtil.getTime());
            return sqlStartDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timestamp compareTimestamp(ArrayList<Timestamp> timestamps) {
        Timestamp result = timestamps.get(0);
        for (Timestamp timestamp : timestamps) {
            if (result.getTime() < timestamp.getTime()) {
                result = timestamp;
            }
        }
        return result;
    }
}
