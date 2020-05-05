/**
 * CalendarTypeAdapter.java
 * Project: GM
 * Created by Jessy Conroy on 2015-04-28
 * Copyright (c) 2015 Good Travel Software. All Rights Reserved
 */

package com.example.mytest.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

class CalendarTypeAdapter implements JsonSerializer<Long>, JsonDeserializer<Long> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

    @Override
    public Long deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) {
        if (jsonElement.isJsonPrimitive()) {
            String value = jsonElement.getAsString();
            if (value != null && !value.trim().equals("")) {
                try {
                    Date date = DATE_FORMAT.parse(value);
                    return date.getTime();
                } catch (ParseException e) {
                    Timber.e(e);
                }
            }
        }
        return 0L;
    }

    @Override
    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(src);
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new JsonPrimitive(DATE_FORMAT.format(cal.getTime()));
    }

}