package com.cocoders.todo.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "todolist")
class TodoListItem(
        @Id @Column(name = "id", updatable = false, nullable = false) var id: UUID,
        var description: String,
        var createdAt: Date = Date()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TodoListItem) return false

        if (id != other.id) return false
        if (description != other.description) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }

    override fun toString(): String {
        return "TodoListItem(id=$id, description='$description', createdAt=$createdAt)"
    }
};
