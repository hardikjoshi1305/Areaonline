package com.areaonline.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ravi on 20/02/18.
 */

public class PrefUtils {
    /**
     * Storing API Key in shared preferences to
     * add it in header part of every retrofit request
     */
    public PrefUtils() {
    }

    private static SharedPreferences getSharedPreferences(Activity context) {
        return context.getSharedPreferences(CONSTANT.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void setPref(Activity context, String str, String str2) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(str,str2);
        editor.commit();
    }
//    public static void saveArrayList(Activity context, ArrayList<HashMap<String,String>> list, String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        editor.putString(key, json);
//        editor.apply();
//
//    }
//
//    public static ArrayList<HashMap<String,String>> getArrayList(Activity context,String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = prefs.getString(key, null);
//        Type type = new TypeToken<ArrayList<HashMap<String,String>>>() {}.getType();
//        return gson.fromJson(json, type);
//    }



    public static String getPref(Activity context,String str) {
        return getSharedPreferences(context).getString(str, "");
    }
}
