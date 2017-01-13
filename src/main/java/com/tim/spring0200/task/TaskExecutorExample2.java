package com.tim.spring0200.task;
import org.springframework.core.task.TaskExecutor;

public class TaskExecutorExample2 {


    public void printMessages() {
        for(int i = 0; i < 2511; i++) {
            System.out.println("==== "+i+" =======");
        }
    }

}