package br.omegafranco.android.todo.data;

import androidx.lifecycle.LiveData;

import br.omegafranco.android.todo.util.RoomDatabase;
import br.omegafranco.android.todo.util.Tools;

public class TodoRepository {
    private static final String tag = TodoDatasource.class.getSimpleName();

    private static TodoRepository instance;
    private TodoDatasource todoDatasource;
    private TodoDao todoDao;

    private TodoRepository(TodoDatasource todoDatasource) {
        this.todoDatasource = todoDatasource;
        this.todoDao = Tools.getInstance().getRoomDatabase().todoDao();
    }

    public static TodoRepository getInstance(TodoDatasource todoDatasource) {
        if (instance == null) {
            instance = new TodoRepository(todoDatasource);
        }
        return instance;
    }

    public LiveData<Result> getTodos() {
        return todoDatasource.getTodos();
    }

    public LiveData<Result> postTodo(Todo todo) {
        return todoDatasource.postTodo(todo);
    }
}
