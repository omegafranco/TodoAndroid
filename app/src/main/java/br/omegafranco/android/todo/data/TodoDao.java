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
    void insert(Todo todo);

    @Insert
    List<Long> insert(List<Todo> todos);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getTodos();

    @Query("SELECT * FROM todo WHERE id=:id")
    LiveData<Todo> getTodo(Integer id);
}
