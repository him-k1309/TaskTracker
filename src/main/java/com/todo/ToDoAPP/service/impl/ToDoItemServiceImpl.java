package com.todo.ToDoAPP.service.impl;

import com.todo.ToDoAPP.entity.ToDoItems;
import com.todo.ToDoAPP.payload.ToDoItemsDTO;
import com.todo.ToDoAPP.repositories.ToDoItemsRepository;
import com.todo.ToDoAPP.service.ToDoItemService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoItemServiceImpl implements ToDoItemService {

    private ToDoItemsRepository toDoItemsRepository;

    private ToDoItemServiceImpl(ToDoItemsRepository toDoItemsRepository){
        this.toDoItemsRepository = toDoItemsRepository;
    }

    @Override
    public Iterable<ToDoItemsDTO> getAll() {

        List<ToDoItems> all = toDoItemsRepository.findAll();
        List<ToDoItemsDTO> allDTO = new ArrayList<>();
        for(ToDoItems item:all) {
            ToDoItemsDTO toDoItemsDTO = new ToDoItemsDTO();
            toDoItemsDTO.setId(item.getId());
            toDoItemsDTO.setDescription(item.getDescription());
            toDoItemsDTO.setIsComplete(item.isComplete());
            toDoItemsDTO.setCreatedAt(item.getCreatedAt());
            toDoItemsDTO.setUpdatedAt(item.getUpdatedAt());
            allDTO.add(toDoItemsDTO);
        }
        return allDTO;
    }

    @Override
    public ToDoItemsDTO getById(long id) {

        ToDoItems item = toDoItemsRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("TodoItem id: " + id + " not found")
                );

        ToDoItemsDTO toDoItemsDTO = new ToDoItemsDTO();
        toDoItemsDTO.setId(item.getId());
        toDoItemsDTO.setDescription(item.getDescription());
        toDoItemsDTO.setIsComplete(item.isComplete());
        toDoItemsDTO.setCreatedAt(item.getCreatedAt());
        toDoItemsDTO.setUpdatedAt(item.getUpdatedAt());
        return toDoItemsDTO;
    }

    @Override
    public ToDoItemsDTO save(ToDoItemsDTO dto) {

        ToDoItems item = new ToDoItems();
        item.setDescription(dto.getDescription());
        item.setComplete(dto.getIsComplete());
        item.setCreatedAt(Instant.now());

        if(Long.valueOf(dto.getId())==null) {
            item.setCreatedAt(Instant.now());
        }
        item.setUpdatedAt(Instant.now());

        toDoItemsRepository.save(item);

        ToDoItemsDTO toDoItemsDTO = new ToDoItemsDTO();
        toDoItemsDTO.setDescription(item.getDescription());
        toDoItemsDTO.setIsComplete(item.isComplete());
        toDoItemsDTO.setCreatedAt(item.getCreatedAt());
        toDoItemsDTO.setUpdatedAt(item.getUpdatedAt());
        return toDoItemsDTO;
    }

    @Override
    public void delete(long id) {

        ToDoItems toDoItem = toDoItemsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("TodoItem id: " + id + " not found")
        );
        toDoItemsRepository.delete(toDoItem);
    }

    @Override
    public ToDoItemsDTO update(long id, ToDoItemsDTO dto) {

        ToDoItems item = toDoItemsRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("TodoItem id: " + id + " not found")
                );
        item.setDescription(dto.getDescription());
        item.setComplete(dto.getIsComplete());

        if(Long.valueOf(dto.getId())==null) {
            item.setCreatedAt(Instant.now());
        }
        item.setUpdatedAt(Instant.now());

        toDoItemsRepository.save(item);

        ToDoItemsDTO toDoItemsDTO = new ToDoItemsDTO();
        toDoItemsDTO.setDescription(item.getDescription());
        toDoItemsDTO.setIsComplete(item.isComplete());
        toDoItemsDTO.setCreatedAt(item.getCreatedAt());
        toDoItemsDTO.setUpdatedAt(item.getUpdatedAt());
        return toDoItemsDTO;
    }

}
