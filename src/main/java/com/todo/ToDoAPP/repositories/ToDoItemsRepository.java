package com.todo.ToDoAPP.repositories;

import com.todo.ToDoAPP.entity.ToDoItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemsRepository extends JpaRepository<ToDoItems, Long> {
}
