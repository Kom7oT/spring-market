package com.geekbrains.springmarket.aop;

import com.geekbrains.springmarket.services.ShoppingCartService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AppLoggingAspect {
    // определяем совет (Advice) "ПЕРЕД" выполнением кода бина (класса)
    @Before("execution(* com.geekbrains.springmarket.services.ProductService.*(..))")
    public void loggingProductMethods(JoinPoint joinPoint) {
        System.out.println("Был вызван метод: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.geekbrains.springmarket.services.ShoppingCartService.addToCart(..))")
    public void cartSum(JoinPoint joinPoint) {
        ShoppingCartService shoppingCartService = (ShoppingCartService) joinPoint.getThis();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.print("Итого в корзине: ");
            for (Object o : args) {
                try {
                    System.out.println(shoppingCartService.getTotalCost((HttpSession) o));
                } catch (ClassCastException e) {
                }
            }
        }
    }
// Не получилось, NPE
    @Around("execution(public * com.geekbrains.springmarket.services..*(..))")
    public void methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (proceedingJoinPoint != null) {
            System.out.println("start profiling");
            long begin = System.currentTimeMillis();
            proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            long duration = end - begin;
        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
            System.out.println("end profiling");
        }
    }
}


