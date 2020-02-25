package pl.edu.pja.prz.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class JsonUtil {
    private JsonUtil() {

    }

    public static String convertToJson(Object input) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return gson.toJson(input);
    }
}
