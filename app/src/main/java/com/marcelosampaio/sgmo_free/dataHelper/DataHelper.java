package com.marcelosampaio.sgmo_free.dataHelper;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHelper {

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //==============================================================================================

    public Integer comparar(String data1, String data2) {
        SimpleDateFormat sdf1 = sdf;

        SimpleDateFormat sdf2 = sdf;
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf1.parse(data1);
            dt2 = sdf1.parse(data2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dt1.compareTo(dt2);
    }
    //==============================================================================================

    public long converteStringDataEmLong(String dia) {
        Date date = null;
        long l = 0;
        try {
            date = sdf.parse(dia);
            l = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }
    //==============================================================================================

    public String convertLongEmStringData(long l) {

        Date dt = new Date(l);
        String df = sdf.format(dt);

        return df;

    }
    //==============================================================================================

    public String converteDataEmString(Date date) {
        return sdf.format(date);
    }
    //==============================================================================================

    public String diaSO() {
        Date date = new Date();
        return sdf.format(date);
    }
    //==============================================================================================

    public Date converteStringEmData(String dia) {
        Date date = null;
        try {
            date = sdf.parse(dia);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    //==============================================================================================


}




