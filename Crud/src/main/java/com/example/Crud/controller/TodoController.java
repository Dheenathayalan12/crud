package com.example.Crud.controller;


import com.example.Crud.models.Todo;
import com.example.Crud.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@Slf4j //we use show messagebox like log.ifo or log.error("error message") or log.warn
public class TodoController {
    @Autowired
    private TodoService todoService;
//    @GetMapping("")
//    String Create(){
//        return "hello";
//    }





    //we change api respon from default to customize
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200",description = "Todo retrived Successfully"),
            @ApiResponse( responseCode = "404",description = "todo was not found..!")

    })

    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodobyId(@PathVariable long id){
//        return new  ResponseEntity<>(todoService.getTodobyid(id), HttpStatus.CREATED);
        // when we run simply it shows system so using this to print on server as not found error

        try {
            Todo gettodo=todoService.getTodobyid(id);
            return new  ResponseEntity<>(gettodo,HttpStatus.OK);
        } catch (RuntimeException Exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    ResponseEntity<List<Todo>> getTodos(){
//        return new  ResponseEntity<>(todoService.getTodobyid(id), HttpStatus.CREATED);
        // when we run simply it shows system so using this to print on server as not found error
        return new  ResponseEntity<>(todoService.gettodos(), HttpStatus.CREATED);

    }


    //page wise like an
    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getpage(@RequestParam int page,@RequestParam int size){
//        return new  ResponseEntity<>(todoService.getTodobyid(id), HttpStatus.CREATED);
        // when we run simply it shows system so using this to print on server as not found error
        return new  ResponseEntity<>(todoService.getallpage(page, size), HttpStatus.OK);

    }

    @PostMapping("/create")
    ResponseEntity<Todo> createuser(@RequestBody Todo todo) {
        return new  ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
        // above are basic run only when the data is insert otherwise is shows system error
//
//        try {
//            Todo create = todoService.createTodo(todo);
//            return new ResponseEntity<>(create, HttpStatus.CREATED);
//        } catch (RuntimeException Exception) {
//            log.info("Error");
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
    }
    @PutMapping
            ResponseEntity<Todo> updatetodos( @RequestBody Todo todo){
            return new ResponseEntity<>(todoService.updatetodos(todo),HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/{id}")
       void deletetodoid(@PathVariable Long id){
                 todoService.deletetodosid(id);

    }
//    @DeleteMapping
//    void deletetodos(@RequestBody Todo todo){
//        todoService.deletetodos(todo);
//    }



}
