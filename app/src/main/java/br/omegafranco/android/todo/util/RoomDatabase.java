package br.omegafranco.android.todo.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import br.omegafranco.android.todo.data.Todo;
import br.omegafranco.android.todo.data.TodoDao;

@Database(entities = {Todo.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static volatile RoomDatabase instance = null;

    public static RoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (RoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, RoomDatabase.class, "todo_database")
                            .build();
                }
            }
        }
        return instance;
    }

    //DAO para Todos
    public abstract TodoDao todoDao();
}
