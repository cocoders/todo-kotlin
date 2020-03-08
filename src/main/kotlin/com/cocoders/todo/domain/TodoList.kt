package com.cocoders.todo.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TodoList: JpaRepository<TodoListItem, UUID> {
}