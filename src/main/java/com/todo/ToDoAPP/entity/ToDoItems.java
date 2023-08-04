package com.todo.ToDoAPP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "todo_items")
public class ToDoItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private boolean isComplete;

    private Instant createdAt;

    private Instant updatedAt;

    @Override
    public String toString(){
        return String.format("ToDoItem{id=%d, description='%s', isComplete='%s', createdAt='%s', updatedAt='%s'} ",
                id,description,isComplete,createdAt,updatedAt);
    }
}
