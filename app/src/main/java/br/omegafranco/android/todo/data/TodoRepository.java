package br.omegafranco.android.todo.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public LiveData<Result> getTodos() {
        return todoDatasource.getTodos();
    }

    public LiveData<Result> postTodo(Todo todo) {
        return todoDatasource.postTodo(todo);
    }

    public LiveData<Result> insertTodo(Todo todo) {
        MutableLiveData<Result> result = new MutableLiveData<>();
        new InsertTodoAsyncTask(todoDao, result).execute(todo);
        return result;
    }
    public LiveData<Result> insertTodos(List<Todo> todos) {
        MutableLiveData<Result> result = new MutableLiveData<>();
        new InsertTodosAsyncTask(todoDao, result).execute(todos);
        return result;
    }
    public LiveData<Result> updateTodo(Todo todo) {
        MutableLiveData<Result> result = new MutableLiveData<>();
        new UpdateTodoAsyncTask(todoDao, result).execute(todo);
        return result;
    }
    public LiveData<Result> deleteTodo(Todo todo) {
        MutableLiveData<Result> result = new MutableLiveData<>();
        new DeleteTodoAsyncTask(todoDao, result).execute(todo);
        return result;
    }

    // Classes para Async com banco de dados.
    private static class InsertTodoAsyncTask extends AsyncTask<Todo, Void, Long> {
        private TodoDao todoDao;
        private MutableLiveData<Result> result;
        private InsertTodoAsyncTask(TodoDao todoDao, MutableLiveData<Result> result) {
            this.todoDao = todoDao;
            this.result = result;
        }

        @Override protected Long doInBackground(Todo... todos) {
            return todoDao.insert(todos[0]);
        }

        @Override
        protected void onPostExecute(Long opResult) {
            super.onPostExecute(opResult);
            if (opResult != null && opResult > 0) {
                result.setValue(new Result.Success<>("Operação realizada com sucesso."));
            }
            else {
                result.setValue(new Result.Error(new Exception("Erro ao efetuar operação no banco de dados.")));
            }
        }
    }
    private static class InsertTodosAsyncTask extends AsyncTask<List<Todo>, Void, List<Long>> {
        private TodoDao todoDao;
        private MutableLiveData<Result> result;
        private InsertTodosAsyncTask(TodoDao todoDao, MutableLiveData<Result> result) {
            this.todoDao = todoDao;
            this.result = result;
        }

        @Override protected List<Long> doInBackground(List<Todo>... todos) {
            return todoDao.insert(todos[0]);
        }

        @Override
        protected void onPostExecute(List<Long> opResult) {
            super.onPostExecute(opResult);
            if (opResult != null && opResult.size() > 0) {
                result.setValue(new Result.Success<>("Operação realizada com sucesso."));
            }
            else {
                result.setValue(new Result.Error(new Exception("Erro ao efetuar operação no banco de dados.")));
            }
        }
    }
    private static class UpdateTodoAsyncTask extends AsyncTask<Todo, Void, Integer> {
        private TodoDao todoDao;
        private MutableLiveData<Result> result;
        private UpdateTodoAsyncTask(TodoDao todoDao, MutableLiveData<Result> result) {
            this.todoDao = todoDao;
            this.result = result;
        }

        @Override protected Integer doInBackground(Todo... todo) {
            return todoDao.update(todo[0]);
        }

        @Override
        protected void onPostExecute(Integer opResult) {
            super.onPostExecute(opResult);
            if (opResult != null && opResult > 0) {
                result.setValue(new Result.Success<>("Operação realizada com sucesso."));
            }
            else {
                result.setValue(new Result.Error(new Exception("Erro ao efetuar operação no banco de dados.")));
            }
        }
    }
    private static class DeleteTodoAsyncTask extends AsyncTask<Todo, Void, Integer> {
        private TodoDao todoDao;
        private MutableLiveData<Result> result;
        private DeleteTodoAsyncTask(TodoDao todoDao, MutableLiveData<Result> result) {
            this.todoDao = todoDao;
            this.result = result;
        }

        @Override protected Integer doInBackground(Todo... todo) {
            return todoDao.update(todo[0]);
        }

        @Override
        protected void onPostExecute(Integer opResult) {
            super.onPostExecute(opResult);
            if (opResult != null && opResult > 0) {
                result.setValue(new Result.Success<>("Operação realizada com sucesso."));
            }
            else {
                result.setValue(new Result.Error(new Exception("Erro ao efetuar operação no banco de dados.")));
            }
        }
    }
}
