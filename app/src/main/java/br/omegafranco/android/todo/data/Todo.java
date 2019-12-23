package br.omegafranco.android.todo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "createdAt")
    private Date createdAt;

    @ColumnInfo(name = "dueAt")
    private Date dueAt;

    public Todo(Integer id, String body, String color, Date createdAt, Date dueAt) {
        this.id = id;
        this.body = body;
        this.color = color;
        this.createdAt = createdAt;
        this.dueAt = dueAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;

    }

}
