package br.omegafranco.android.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    Long insert(Todo todo);

    @Insert
    List<Long> insert(List<Todo> todos);

    @Update
    Integer update(Todo todo);

    @Delete
    Integer delete(Todo todo);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getTodos();

    @Query("SELECT * FROM todo WHERE id=:id")
    LiveData<Todo> getTodo(Integer id);
}
