package com.gmd.common.datamodel;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GsonConverter {
    private static Gson gson = new GsonBuilder().create();

    public static void configTypeAdapterFactories(List<TypeAdapterFactory> factoryList) {
        if(factoryList == null) {
            factoryList = new ArrayList<>();
        }

        GsonBuilder builder = new GsonBuilder();
        for(TypeAdapterFactory f : factoryList) {
            builder.registerTypeAdapterFactory(f);
        }
        changeGson(builder.create());
    }

    public static void configTypeAdapter(Type type, Object typeAdapter) {
        GsonBuilder builder = new GsonBuilder().serializeNulls();
        builder.registerTypeAdapter(type, typeAdapter);
        changeGson(builder.create());
    }

    public static void changeGson(Gson gson) {
        GsonConverter.gson = gson;
    }

    /**
     * Convert Object To Json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * Decoder method for com.google.gson.JsonObject
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T decode(JsonElement json, Class<T> classOfT) {
        if(json == null || json.isJsonNull()) {
            json = new JsonObject();
        }
        T result = decode(json.toString(), classOfT);

        if(result == null) {
            try {
                result = classOfT.getDeclaredConstructor().newInstance();
            } catch (Exception reflectException) {
            }
        }

        return result;
    }

    /**
     * Decoder method for json string
     *
     * @param jsonString
     * @param typeOfT
     * @return
     */
    public static <T> T decode(String jsonString, Type typeOfT) {
        try {
            if (TextUtils.isEmpty(jsonString)) {
                return null;
            }

            //remove '[]' to avoid decode error caused by php type ambiguity
            Pattern pattern = Pattern.compile("\\{\"[a-zA-Z_]+?\":\\[\\],?");
            Matcher matcher = pattern.matcher(jsonString);
            jsonString = matcher.replaceAll("\\{");

            pattern = Pattern.compile(",\"[a-zA-Z_]+?\":\\[\\]");
            matcher = pattern.matcher(jsonString);
            jsonString = matcher.replaceAll("");

            return gson.fromJson(jsonString, typeOfT);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
