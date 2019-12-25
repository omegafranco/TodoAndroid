package br.omegafranco.android.todo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.omegafranco.android.todo.data.Result;
import br.omegafranco.android.todo.data.Todo;
import br.omegafranco.android.todo.data.TodoDatasource;
import br.omegafranco.android.todo.data.TodoRepository;

public class TodoCollectionViewModel extends ViewModel {
    private static final String tag = TodoDatasource.class.getSimpleName();

    private TodoRepository todoRepository;
    private MutableLiveData<List<Todo>> todos;

    public TodoCollectionViewModel(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;

        todos = (MutableLiveData<List<Todo>>)Transformations.map(this.todoRepository.getTodos(), result -> {
            if (result instanceof Result.Success) {
                return ((Result.Success<List<Todo>>) result).getData();
            }
            else {
                return new ArrayList<Todo>();
            }
        });
    }

    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

    public void postTodo() {
        Todo todo = new Todo(8, "Meu oitavo todo", "black", new Date(), new Date());
        todoRepository.insertTodo(todo);
    }
}
