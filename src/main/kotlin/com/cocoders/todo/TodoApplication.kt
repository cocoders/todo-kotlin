package com.cocoders.todo

import com.cocoders.todo.domain.TodoList
import com.cocoders.todo.domain.TodoListItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TodoApplication
fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args)
}

@RestController
class TodoLisController constructor(@Autowired val repository: TodoList){
	@GetMapping("/todo-list-item")
	fun getItems(): MutableList<TodoListItem> {
		return repository.findAll()
	}
	@PostMapping("/todo-list-item")
	fun saveItem(@RequestBody listItem: TodoListItem): TodoListItem {
		return repository.saveAndFlush(listItem)
	}
}
