package com.example.demo.domain.comment.service

import com.example.demo.domain.comment.dto.AddCommentRequest
import com.example.demo.domain.comment.dto.CommentResponse
import com.example.demo.domain.comment.dto.UpdateCommentRequest
import com.example.demo.domain.comment.model.Comment

interface CommentService {

    fun addComment(todoId: Long, request: AddCommentRequest): CommentResponse

    fun updateComment(todoId: Long, commentId:Long, request: UpdateCommentRequest): CommentResponse

    fun deleteComment(todoId: Long, commentId: Long)
}