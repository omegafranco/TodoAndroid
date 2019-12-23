package br.omegafranco.android.todo.util;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {
    @TypeConverter
    public Date longToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter public Long dateToLong(Date date) {
        if (date == null) {
            return null;
        }
        else {
            return date.getTime();
        }
    }
}
