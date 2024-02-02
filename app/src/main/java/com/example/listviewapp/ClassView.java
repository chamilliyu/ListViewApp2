package com.example.listviewapp;

public class ClassView {

    private String className;
    private String toDo;
    private String time;

    public ClassView(String className, String toDo, String time) {
        this.className = className;
        this.toDo = toDo;
        this.time = time;
    }

    public String getClassName() {
        return className;
    }

    public String getToDo() {
        return toDo;
    }

    public String getTime() {
        return time;
    }

  // used for edit button
    public void setClassName(String className) {
        this.className = className;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
