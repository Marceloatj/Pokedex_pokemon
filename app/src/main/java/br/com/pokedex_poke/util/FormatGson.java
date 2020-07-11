package br.com.pokedex_poke.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatGson {


    public static Gson gsonDateInt(){
        GsonBuilder builder = new GsonBuilder().setDateFormat(FormatDate.yyyyMMddHHmmss);

// Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  {
                try{
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }catch (NumberFormatException ex){
                    try {
                        return new SimpleDateFormat(FormatDate.yyyyMMddHHmmss).parse(json.getAsString());
                    } catch (ParseException e) {
                        try {
                            return new SimpleDateFormat(FormatDate.yyyyMMdd).parse(json.getAsString());
                        } catch (ParseException exc) {
                            exc.printStackTrace();
                            return null;
                        }
                    }
                }

            }
        });

        Gson gson = builder.create();
        return gson;
    }


}
