package com.todo.ToDoAPP.service;

import com.todo.ToDoAPP.payload.ToDoItemsDTO;

public interface ToDoItemService {
    public Iterable<ToDoItemsDTO> getAll();
    public ToDoItemsDTO getById(long id);
    public ToDoItemsDTO save(ToDoItemsDTO dto);
    public void delete(long id);
    ToDoItemsDTO update(long id, ToDoItemsDTO dto);
}
