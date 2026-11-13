package com.example.Crud.service;

import com.example.Crud.models.Todo;
import com.example.Crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;




    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }
    public Todo getTodobyid(long id){
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("todo not foound"));
    }

    public Page<Todo> getallpage(int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        return todoRepository.findAll(pageable);
    }


    public List<Todo> gettodos(){
        return todoRepository.findAll();
    }
    public Todo updatetodos(Todo todo){
        return todoRepository.save(todo);
    }
    public void deletetodosid(Long id) {
        todoRepository.delete(getTodobyid(id));
    }
//    public void deletetodos(Todo todo){
//         todoRepository.deleteAll(todo);
//    }

}
