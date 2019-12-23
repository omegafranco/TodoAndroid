package br.omegafranco.android.todo.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.omegafranco.android.todo.data.TodoDatasource;
import br.omegafranco.android.todo.data.TodoRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull @Override @SuppressWarnings("unchecked") public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TodoCollectionViewModel.class)) {
            return (T) new TodoCollectionViewModel(TodoRepository.getInstance(new TodoDatasource()));
        }
        else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
