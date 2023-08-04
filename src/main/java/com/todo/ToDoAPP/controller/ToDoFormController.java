package com.todo.ToDoAPP.controller;

import com.todo.ToDoAPP.payload.ToDoItemsDTO;
import com.todo.ToDoAPP.service.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ToDoFormController {


    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(@ModelAttribute("dto") ToDoItemsDTO dto){

        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid @ModelAttribute("dto") ToDoItemsDTO dto){

        toDoItemService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {

        toDoItemService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        ToDoItemsDTO dto = toDoItemService
                .getById(id);

        model.addAttribute("todo", dto);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid @ModelAttribute("dto") ToDoItemsDTO dto, BindingResult result, Model model) {

        toDoItemService.update(id, dto);
        return "redirect:/";
    }
}
