package com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator;



import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class TimestampManager {
    final static String TIME = "T00:00:00.402Z";

    public List<String> referenceTimestampConstructor(){

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;      // 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int[] intArray = new int[]{7, 14, 30};
        String t = year + "-" + month + "-" + day + TIME;

        List<String> listOfTimestamps = new ArrayList<>();
        for (int i=0; i<3; i++){
            int day2 = day - intArray[i];
            if (day2<0){
                day2 = day2 + 30;
                int month2 = month -1;
                if (month2<=0){
                    month2 = month2 + 12;
                    int year2 = year -1;
                    String time = year2 + "-" + month2 + "-" + day2 + TIME + "/" + t;
                    listOfTimestamps.add(time);
                }else{String time = year + "-" + month2 + "-" + day2 + TIME + "/" + t;
                    listOfTimestamps.add(time);
                }

            }else{
                String time = year + "-" + month + "-" + day2 + TIME + "/" + t;
                listOfTimestamps.add(time);
            }



        }

        return listOfTimestamps;
    }

    public List<String> predictionTimestamp(){

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;      // 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int[] intArray = new int[] {3, 4, 5};
        List<String> listOfTimestamps = new ArrayList<>();
        for (int i=0; i<3; i++){
            int day2 = day + intArray[i];
            if (day2>30) {
                day2 = day2 - 30;
                int month2 = month + 1;
                if (month2 > 12) {
                    int month3 = month2 - 12;
                    int year2 = year + 1;
                    String time = year2 + "-" + month3 + "-" + day2 + TIME;
                    listOfTimestamps.add(time);
                }
                String time = year + "-" + month2 + "-" + day2 + TIME;
                listOfTimestamps.add(time);

            }else{
                String time = year + "-" + month + "-" + day2 + TIME;
                listOfTimestamps.add(time);
            }
        }

        return listOfTimestamps;
    }

    public ArrayList<Pair<String, String>> listOfTimestampsForScenarios(){
        TimestampManager timestampManager = new TimestampManager();
        List<String> a = timestampManager.predictionTimestamp();
        List<String> b = timestampManager.referenceTimestampConstructor();
        ArrayList<Pair<String, String>> al = new ArrayList<Pair<String, String>>();

        for(int i=0; i<a.size(); i++){
            for (int j=0; j<b.size(); j++){
                al.add(new Pair<String, String> (a.get(i), b.get(j)));
            }

        }






        return al;
    }
    public static void main(String[] args) throws Exception {
        TimestampManager timestampManager = new TimestampManager();
        List<String> a = timestampManager.predictionTimestamp();
        List<String> b = timestampManager.referenceTimestampConstructor();

        Iterator<String> iteratorA = a.iterator();
        Iterator<String> iteratorB = b.iterator();
        String time1 = null;
        String time2 = null;

        for(int i=0; i<a.size(); i++){
            for (int j=0; j<b.size(); j++){
             System.out.println(a.get(i) + "######" + b.get(j));
            }

        }

        ArrayList<Pair<String, String>> test= timestampManager.listOfTimestampsForScenarios();
        Pair<String, String> ggg = test.get(0);
        String gg = ggg.getKey();
        System.out.println(gg);

        /*Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);      // 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String time = "T00:00:00.402Z";


        System.out.printf("Now is %4d-%02d-%02d" + time,year, month+1, day);*/
    }
}
