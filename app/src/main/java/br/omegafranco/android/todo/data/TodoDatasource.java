package br.omegafranco.android.todo.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import br.omegafranco.android.todo.BuildConfig;
import br.omegafranco.android.todo.util.GsonGetRequest;
import br.omegafranco.android.todo.util.GsonPostRequest;
import br.omegafranco.android.todo.util.Tools;


public class TodoDatasource {
    private static final String tag = TodoDatasource.class.getSimpleName();

    private String baseUrl = BuildConfig.SERVER_URL;
    private final RequestQueue requestQueue = Tools.getInstance().getRequestQueue();

//    private static TodoDatasource instance;
//    private TodoDatasource() {
//    }

//    public static TodoDatasource getInstance() {
//        if (instance == null) {
//            instance = new TodoDatasource();
//        }
//        return instance;
//    }

    public LiveData<Result> getTodos() {
        MutableLiveData<Result> resultLiveData = new MutableLiveData<>();
        String url = String.format(Locale.getDefault(),"%s/todo",baseUrl);
        Type listType = new TypeToken<List<Todo>>(){}.getType();
        GsonGetRequest<List<Todo>> todoGsonGetRequest = new GsonGetRequest<>(url, listType, null, todosResponse -> {
            List<Todo> todos = todosResponse;
            resultLiveData.setValue(new Result.Success<>(todos));
        }, error -> {
            Log.d(tag, "ERROR " + error.toString());
            resultLiveData.setValue(new Result.Error(error));
        });
        int socketTimeout = 10000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        todoGsonGetRequest.setRetryPolicy(policy);
        requestQueue.add(todoGsonGetRequest);
        return resultLiveData;
    }

    public LiveData<Result> postTodo(Todo todo) {
        MutableLiveData<Result> resultLiveData = new MutableLiveData<>();
        String url = String.format(Locale.getDefault(),"%s/todo",baseUrl);
        Type todoType = new TypeToken<Todo>(){}.getType();
        String body = Tools.getInstance().getGson().toJson(todo);
        GsonPostRequest<Todo> todoGsonPostRequest = new GsonPostRequest<>(url, body, todoType, null, response -> {
            resultLiveData.setValue(new Result.Success<>(response));
        }, error -> {
            Log.d(tag, "ERROR " + error.toString());
            resultLiveData.setValue(new Result.Error(error));
        });
        int socketTimeout = 10000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        todoGsonPostRequest.setRetryPolicy(policy);
        requestQueue.add(todoGsonPostRequest);
        return resultLiveData;
    }
}
