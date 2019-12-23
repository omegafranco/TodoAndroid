package br.omegafranco.android.todo.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.omegafranco.android.todo.R;
import br.omegafranco.android.todo.data.Todo;
import br.omegafranco.android.todo.databinding.TodoListItemBinding;

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder> {
    private List<Todo> todos;

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TodoListItemBinding todoListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.todo_list_item, viewGroup, false);
        return new TodoViewHolder(todoListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder todoViewHolder, int i) {
        Todo aTodo = todos.get(i);
        todoViewHolder.todoListItemBinding.setTodo(aTodo);
    }

    @Override
    public int getItemCount() {
        if (todos != null) {
            return todos.size();
        } else {
            return 0;
        }
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        private TodoListItemBinding todoListItemBinding;

        public TodoViewHolder(@NonNull TodoListItemBinding todoListItemBinding) {
            super(todoListItemBinding.getRoot());
            this.todoListItemBinding = todoListItemBinding;
        }
    }
}
