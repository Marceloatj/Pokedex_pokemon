//package br.com.pokedex_poke.dao.generic;
//
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteStatement;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.br.com.pokedex_poke.util.ArrayList;
//import java.br.com.pokedex_poke.util.Date;
//import java.br.com.pokedex_poke.util.HashMap;
//import java.br.com.pokedex_poke.util.List;
//import java.br.com.pokedex_poke.util.Map;
//
//import br.com.arcuschk.br.com.pokedex_poke.util.date.FormatDate;
//
//
//public class FieldsBind {
//
//    public static String mountInsert(Class clazz) {
//        ClassBinding classBinding = (ClassBinding) clazz.getAnnotation(ClassBinding.class);
//        String query = "INSERT INTO " + classBinding.value() + " (";
//        int quantidade = 0;
//        for (Field field : getFieldsAnnotation(clazz)) {
//            quantidade++;
//            query += field.getAnnotation(FieldBinding.class).value() + ",";
//        }
//
//        query = removeLastChar(query);
//
//        query += ") VALUES (";
//
//        for (int i = 0; i < quantidade; i++) {
//            query += "?,";
//        }
//
//        query = removeLastChar(query);
//        query += ");";
//
//        return query;
//    }
//
//
//    public static void bindInsert(JSONArray json, Class clazz, SQLiteStatement statement, SQLiteDatabase database) {
//
//        GsonBuilder builder = new GsonBuilder();
//
//// Register an br.com.pokedex_poke.adapter to manage the date types as long values
//        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
//
//            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  {
//                try{
//                    return new Date(json.getAsJsonPrimitive().getAsLong());
//                }catch (NumberFormatException ex){
//                    try {
//                        return new SimpleDateFormat(FormatDate.yyyyMMddHHmmss).parse(json.getAsString());
//                    } catch (ParseException e) {
//                        try {
//                            return new SimpleDateFormat(FormatDate.yyyyMMdd).parse(json.getAsString());
//                        } catch (ParseException exc) {
//                            exc.printStackTrace();
//                            return null;
//                        }
//                    }
//                }
//
//            }
//        });
//
//        Gson gson = builder.create();
//
//        //Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
//        //Gson gson = new Gson();
//
//        List<Field> fields = getFieldsAnnotation(clazz);
//        Map<String, Method> map = new HashMap<String, Method>();
//        String name_field;
//        String name_method;
//        Method method;
//
//        try {
//
//            for (Field f : fields) {
//                name_field = f.getName();
//                name_field = name_field.substring(0, 1).toUpperCase().concat(name_field.substring(1));
//                name_method = "get" + name_field;
//                method = clazz.getDeclaredMethod(name_method, null);
//                map.put(f.getName(), method);
//            }
//
//            statement = database.compileStatement(mountInsert(clazz));
//            database.beginTransaction();
//            Object obj;
//            int position_bind;
//
//            for (int i = 0; i < json.length(); i++) {
//                try {
//                    position_bind = 1;
//
//                    JSONObject jsonObject = json.getJSONObject(i);
//
//                    obj = gson.fromJson(jsonObject.toString(), clazz);
//                    statement.clearBindings();
//
//                    for (Field f : fields) {
//
//                        if(f.isAnnotationPresent(NotSerialized.class) && f.isAnnotationPresent(FieldBinding.class)){
//                            statement.bindString(position_bind, String.valueOf( jsonObject.get(f.getAnnotation(FieldBinding.class).value()) ) );
//                        }
//                        else
//                            statement = bindField(map.get(f.getName()), position_bind, statement, f.getType(), obj);
//
//                        position_bind++;
//                    }
//                        statement.executeInsert();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//            database.setTransactionSuccessful();
//            database.endTransaction();
//
//        }
//        System.gc();
//    }
//
//
//    private static SQLiteStatement bindField(Method m, int position, SQLiteStatement statement, Type typeField, Object obj) {
//
//        Double value;
//        String bind;
//
//        try {
//            if (typeField == Integer.class) {
//                String s = String.valueOf(m.invoke(obj, new Object[]{}));
//                if (s.equalsIgnoreCase("null"))
//                    statement.bindLong(position, 0);
//                else
//                    statement.bindString(position, String.valueOf(m.invoke(obj, new Object[]{})));
//            } else if (typeField == String.class) {
//                bind = (String) m.invoke(obj, new Object[]{});
//                if (bind == null) {
//                    bind = "";
//                }
//                statement.bindString(position, bind);
//            } else if (typeField == BigDecimal.class) {
//                value = ((BigDecimal) m.invoke(obj, new Object[]{})).doubleValue();
//                statement.bindDouble(position, value);
//            } else if (typeField == Date.class) {
//                try{
//                    statement.bindString(position, FormatDate.dateToString((Date) m.invoke(obj, new Object[]{}), FormatDate.yyyyMMddHHmmss) );
//                }catch (Exception e){
//                    try{
//                        statement.bindString(position, FormatDate.dateToString((Date) m.invoke(obj, new Object[]{}), FormatDate.yyyyMMdd) );
//                    }catch (Exception ex){
//                        statement.bindNull(position);
//                    }
//                }
//
////				statement.bind(position, (Long) m.invoke(obj, new Object[] {}));
//            } else if (typeField == Double.class) {
//                value = (Double) m.invoke(obj, new Object[]{});
//                statement.bindDouble(position, value);
//            } else if (typeField == double.class) {
//                value = (Double) m.invoke(obj, new Object[]{});
//                statement.bindDouble(position, value);
//            } else if(typeField instanceof Enum){
//                Object o = (Enum) m.invoke(obj,new Object[]{});
//                statement.bindString(position, o.toString());
//            }
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            statement.bindNull(position);
//        } finally {
//
//        }
//
//        return statement;
//
//    }
//
//    private static List<Field> getFieldsAnnotation(Class clazz) {
//        List<Field> fields = new ArrayList<Field>();
//        for (Field field : clazz.getDeclaredFields()) {
//            if (field.isAnnotationPresent(FieldBinding.class)) {
//                fields.add(field);
//            }
//        }
//        return fields;
//    }
//
//
//    private static String removeLastChar(String query) {
//        if (query.length() > 0) {
//            query = query.substring(0, query.length() - 1);
//        }
//        return query;
//    }
//
//}
