package com.springMVCProject.Aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger=Logger.getLogger(getClass().getName());
	
	//for all methode of controller pkg
	@Pointcut("execution(* com.springMVCProject.Controller.*.*(..))")
	private void forController() {}
	
	//for all methode of service pkg
	@Pointcut("execution(* com.springMVCProject.Service.*.*(..))")
	private void forService() {}
	
	//for all methode of DAO pkg
	@Pointcut("execution (* com.springMVCProject.DAO.*.*(..)) ")
	private void forDao() {}
	
	//combining pointcut expression
	@Pointcut("forController() || forService() || forDao()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void beforeMethod(JoinPoint joinPoint) {
		
		// display method we are calling
		String method=joinPoint.getSignature().toShortString();
		logger.info("=====>> in @Before advice: calling to method: " +method);
	
		// display the arguments to the method
		
		// get the arguments
		Object[] args = joinPoint.getArgs();
				
		// loop thru and display args
		for (Object tempArg : args) {
			logger.info("=====>> argument: " + tempArg);
		}
	}
	
	
	// add @AfterReturning advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void AfterReturninFromMethod(JoinPoint joinPoint, Object result) {
		
		// display method we are returning from
		String theMethod = joinPoint.getSignature().toShortString();
		logger.info("=====>> in @AfterReturning advice:  returning from method: " + theMethod);
				
		// display data returned
		logger.info("=====>> result: " + result);
	}
}
