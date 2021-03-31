package pawel.szafraniec.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for test classes
 * 
 * @author Pawel Szafraniec
 * @version 4.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TestInfo {
   
    /**
     * Enum representing priority of test class
     */
    public enum TestPriority
    {
        LOW,
        MEDIUM,
        HIGH
    }
   
    /**
     * Parameter of the annotation representing test class priority
     * 
     * @return test class priority
     */
    TestPriority testPriority() default TestPriority.MEDIUM;

    /**
     * String parameter representing author of the test class
     * 
     * @return String representing author
     */
    String createdBy() default "Pawel Szafraniec";

    /**
     * Array of Strings representing tags
     * 
     * @return tags tags of test class
     */
    String [] tags() default "test"; 
    
}
