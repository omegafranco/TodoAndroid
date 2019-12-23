package br.omegafranco.android.todo.data;

public class TodoRepository {
    private static final String tag = TodoDatasource.class.getSimpleName();

    private static TodoRepository instance;
    private TodoDatasource todoDatasource;
    private TodoRepository(TodoDatasource todoDatasource) {
        this.todoDatasource = todoDatasource;
    }

    public static TodoRepository getInstance(TodoDatasource todoDatasource) {
        if (instance == null) {
            instance = new TodoRepository(todoDatasource);
        }
        return instance;
    }
}
