package br.omegafranco.android.todo.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

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

    public LiveData<Result> loadTodos() {
        return todoDatasource.getTodos();
    }

    public LiveData<Result> postTodo(Todo todo) {
        return todoDatasource.postTodo(todo);
    }

    public LiveData<Result> getTodos() {
        LiveData<List<Todo>> result = LiveDataReactiveStreams.fromPublisher(todoDao.getTodos());
        return Transformations.map(result, todos -> {
            if (todos != null && todos.size() > 0) {
                return new Result.Success<>(todos);
            }
            else {
                return new Result.Error(new Exception("Erro ao realizar operação no banco de dados."));
            }
        });
    }
    public LiveData<Result> insertTodo(Todo todo) {
        LiveData<Long> result = LiveDataReactiveStreams.fromPublisher(todoDao.insert(todo).toFlowable());
        return Transformations.map(result, todoId -> {
            if (todoId != null && todoId > 0) {
                return new Result.Success<>("Operação realizada com sucesso.");
            }
            else {
                return new Result.Error(new Exception("Erro ao realizar operação no banco de dados."));
            }
        });
    }
    public LiveData<Result> insertTodos(List<Todo> todos) {
        LiveData<Integer> result = LiveDataReactiveStreams.fromPublisher(todoDao.insert(todos).toFlowable());
        return Transformations.map(result, todoId -> {
            if (todoId != null && todoId > 0) {
                return new Result.Success<>("Operação realizada com sucesso.");
            }
            else {
                return new Result.Error(new Exception("Erro ao realizar operação no banco de dados."));
            }
        });
    }
    public LiveData<Result> updateTodo(Todo todo) {
        LiveData<Integer> result = LiveDataReactiveStreams.fromPublisher(todoDao.update(todo).toFlowable());
        return Transformations.map(result, todoId -> {
            if (todoId != null && todoId > 0) {
                return new Result.Success<>("Operação realizada com sucesso.");
            }
            else {
                return new Result.Error(new Exception("Erro ao realizar operação no banco de dados."));
            }
        });
    }
    public LiveData<Result> deleteTodo(Todo todo) {
        LiveData<Integer> result = LiveDataReactiveStreams.fromPublisher(todoDao.delete(todo).toFlowable());
        return Transformations.map(result, todoId -> {
            if (todoId != null && todoId > 0) {
                return new Result.Success<>("Operação realizada com sucesso.");
            }
            else {
                return new Result.Error(new Exception("Erro ao realizar operação no banco de dados."));
            }
        });
    }
}
