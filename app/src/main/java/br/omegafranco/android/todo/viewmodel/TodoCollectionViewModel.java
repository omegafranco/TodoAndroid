package br.omegafranco.android.todo.viewmodel;

import androidx.lifecycle.ViewModel;

import br.omegafranco.android.todo.data.TodoDatasource;
import br.omegafranco.android.todo.data.TodoRepository;

public class TodoCollectionViewModel extends ViewModel {
    private static final String tag = TodoDatasource.class.getSimpleName();

    private TodoRepository todoRepository;

    public TodoCollectionViewModel(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
}
