package com.example.demo.domain.todo.model

import com.example.demo.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class Todo(

    @Column(name = "title")
    var title : String,

    @Column(name = "description")
    var description : String,

//    @Column(name = "author")
//    val author : String,

    @Column(name = "create_date")
    val createDate : LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_date")
    var modifiedDate : LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        description = description
    )
}
