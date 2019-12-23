package br.omegafranco.android.todo.util;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    private static final String tag = Tools.class.getSimpleName();

    private static Tools instance;
    private Gson gson;
    private RequestQueue requestQueue;
    private Application application;
    private RoomDatabase roomDatabase;
    private SimpleDateFormat dateFormat;

    private Tools(){}

    public static synchronized Tools getInstance() {
        if (Tools.instance == null){
            Tools.instance = new Tools();
        }
        return Tools.instance;
    }

    public Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            JsonSerializer<Date> dateJsonSerializer = ( src,  typeOfSrc,  context) ->  new JsonPrimitive(src.getTime());;
            JsonDeserializer<Date> dateJsonDeserializer = ( json, typeOfT, context) -> new Date(json.getAsLong());
            gsonBuilder.registerTypeAdapter(Date.class, dateJsonDeserializer );
            gsonBuilder.registerTypeAdapter(Date.class, dateJsonSerializer);
            gson = gsonBuilder.create();
        }
        return gson;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        requestQueue.start();
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public SimpleDateFormat getDateFormat() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        return dateFormat;
    }

    public RoomDatabase getRoomDatabase() {
        return roomDatabase;
    }

    public void setRoomDatabase(RoomDatabase roomDatabase) {
        this.roomDatabase = roomDatabase;
    }
}
