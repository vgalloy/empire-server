package com.vgalloy.empire.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent Galloy on 01/03/18.
 * <p>
 * The goal is to logs all information given to the method annotate with {@link FullLog} and the result.
 * <p>
 * If the class AND the method are annotated with {@link FullLog}, only the method annotation must be applied.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public class LoggerAspect {

    /**
     * 1. Analyse le point cut afin de trouver le nom et les arguments de la fonction appelé et les log en fonction du
     * niveau de log passé en paramètre.
     * 2. Execute la méthode
     * 3. Affiche le resultat
     *
     * @param joinPoint Le joinPoint servant de reference vers le file d'execution et la méthode encapsulée
     * @param logLevel  Le niveau de log attendu
     * @return Le resultat de la methode encapsulée par l'aspect
     * @throws Throwable La méthode encapsulée peux jetter n'importe quel type de Throwable
     */
    private static Object displayLog(ProceedingJoinPoint joinPoint, LogLevel logLevel) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        StringBuilder stringBuilder = new StringBuilder("[ START ] : ")
                .append(joinPoint.getSignature().getName())
                .append("(");
        for (Object o : joinPoint.getArgs()) {
            stringBuilder.append(o.toString());
        }
        stringBuilder.append(")");
        LogLevel.printLog(logger, logLevel, stringBuilder.toString());

        Object result = joinPoint.proceed();

        stringBuilder = new StringBuilder("[ END   ] : ")
                .append(joinPoint.getSignature().getName())
                .append(" ==> ")
                .append(result);
        LogLevel.printLog(logger, logLevel, stringBuilder.toString());
        return result;
    }

    /**
     * Pointcut creation.
     * 1. @within is used to detect class annotated with {@link FullLog}
     * 2. @annotation is used to detect method annotated with {@link FullLog}
     * <p/>
     * The main issues here to handle double annotation : one on class and one on method. If both are present only
     * method must be applied
     * If pointcut define an OR ( || ) annotation parameter is field with the second part of the OR even if this one is
     * empty.
     * <p>
     * For example  : @within(methodLog) || @annotation(methodLog)
     * if class is annotated but the method is not, pointcut will be call but parameter "methodLog" will be empty.
     *
     * @param joinPoint the jointPoint representing wrapped method
     * @param methodLog the annotation related to the method, can be null
     * @param classLog  the annotation related to the class, can be null
     * @return the wrapped method result
     * @throws Throwable forward method possible throwable
     */
    @Around("(@within(methodLog) || @annotation(methodLog)) && (@annotation(classLog) || @within(classLog))")
    public final Object logForClass(ProceedingJoinPoint joinPoint, FullLog methodLog, FullLog classLog) throws Throwable {
        if (methodLog != null) {
            return displayLog(joinPoint, methodLog.value());
        }
        return displayLog(joinPoint, classLog.value());
    }
}
