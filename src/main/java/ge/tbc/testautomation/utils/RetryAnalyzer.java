package ge.tbc.testautomation.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count;
    @Override
    public boolean retry(ITestResult iTestResult) {
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(RetryCount.class)){
            RetryCount annotation = method
                    .getAnnotation(RetryCount.class);

            if (count < annotation.count()){
                count++;
                return true;
            }
        }
        return false;
    }
}