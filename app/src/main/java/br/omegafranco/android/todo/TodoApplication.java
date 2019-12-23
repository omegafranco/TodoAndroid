package br.omegafranco.android.todo;

import android.app.Application;

import com.android.volley.toolbox.Volley;

import br.omegafranco.android.todo.util.Tools;

public class TodoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Tools tools = Tools.getInstance();
        tools.setApplication(this);
        tools.setRequestQueue(Volley.newRequestQueue(this));

    }
}
