package com.example.leaftest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {

    @Around("execution(* com.example.leaftest..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        Object[] args = joinPoint.getArgs();
        try {
            System.out.println("ARGUMENT: " + args.hashCode());
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            for(Object arg : args){
                System.out.println("ARGUMENT: " + arg.toString());
            }
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}