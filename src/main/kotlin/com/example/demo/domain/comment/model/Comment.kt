package com.example.demo.domain.comment.model

import com.example.demo.domain.comment.dto.CommentResponse
import com.example.demo.domain.todo.model.Todo
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "comment")
@Entity
class Comment (

    @Column(name = "content")
    var content : String,

    @Column(name = "password")
    val password : String,

    @Column(name = "create_date")
    val createDate : LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_date")
    var modifiedDate : LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        content = content,
        password = password,
    )
}