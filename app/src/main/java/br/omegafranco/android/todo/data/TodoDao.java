package br.omegafranco.android.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface TodoDao {

    @Insert
    Maybe<Long> insert(Todo todo);

    @Insert
    Completable insert(List<Todo> todos);

    @Update
    Single<Integer> update(Todo todo);

    @Delete
    Single<Integer> delete(Todo todo);

    @Query("SELECT * FROM todo")
    Flowable<List<Todo>> getTodos();

    @Query("SELECT * FROM todo WHERE id=:id")
    Flowable<Todo> getTodo(Integer id);
}
