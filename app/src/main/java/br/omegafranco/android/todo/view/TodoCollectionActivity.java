package br.omegafranco.android.todo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.omegafranco.android.todo.R;
import br.omegafranco.android.todo.data.Todo;
import br.omegafranco.android.todo.databinding.ActivityTodoCollectionBinding;
import br.omegafranco.android.todo.viewmodel.TodoCollectionViewModel;
import br.omegafranco.android.todo.viewmodel.ViewModelFactory;

public class TodoCollectionActivity extends AppCompatActivity {
    private static final String tag = TodoCollectionActivity.class.getSimpleName();

    TodoCollectionViewModel todoCollectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoCollectionViewModel = ViewModelProviders.of(this, new ViewModelFactory()).get(TodoCollectionViewModel.class);
        ActivityTodoCollectionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_collection);
        binding.setTodoCollectionViewModel(todoCollectionViewModel);
        binding.setLifecycleOwner(this);

        binding.getTodoCollectionViewModel().getTodos().observe(this, todos -> {
            Log.d(tag, "recebi resultado");
            for(Todo todo: todos) {
                Log.d(tag, todo.getBody());
            }
        });
    }
}
