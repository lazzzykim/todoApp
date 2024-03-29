package com.example.demo.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

//@Aspect
//@Component
class TestAop {

    @Around("execution(* com.example.demo.domain.todo.service.TodoService.getTodoById(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint) {
        println("AOP START!!!")
        joinPoint.proceed()
        println("APO END!!!")
    }
}