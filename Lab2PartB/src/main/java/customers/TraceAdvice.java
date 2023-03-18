package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Configuration
public class TraceAdvice {
    @After("execution(* customers.EmailSender.sendEmail(..)) && args(email, message)")
    public void afterExecution(JoinPoint joinPoint, String email, String message){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        EmailSender emailSender  = (EmailSender) joinPoint.getTarget();
        System.out.println(dtf.format(now) + " method=" + joinPoint.getSignature().getName() +
                " address = " + email + " message= " + message + " outgoingmailserver = "+ emailSender.getOutgoingMailServer());

    }
    @Around("execution(* customers.CustomerDAO.*(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        System.out.println(totaltime);
        return retVal;
    }

}
