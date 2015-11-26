package junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit4测试
 * BeforeClass和AfterClass只会加载一次，比如加载配置文件，初始化spring
 * Before和After在每个Test前后都会运行，比如清理数据
 * Test的方法名随意
 * Ignore忽略该测试
 * @author angilin
 *
 */
public class JUnit4Test {   
    @Before  
    public void before() {   
        System.out.println("@Before");   
    }   
    
    @Test  
         /**  
          *Mark your test cases with @Test annotations.   
          *You don’t need to prefix your test cases with “test”.  
          *tested class does not need to extend from “TestCase” class.  
          */  
    public void test() {   
        System.out.println("@Test");   
        assertEquals(5 + 5, 10);   
    }   
    
    @Ignore  
    @Test  
    public void testIgnore() {   
        System.out.println("@Ignore");   
    }   
    
    @Test(timeout = 50)   
    public void testTimeout() {   
        System.out.println("@Test(timeout = 50)");   
        assertEquals(5 + 5, 10);   
    }   
    
    
    @Test(timeout = 50)   
    public void aaaa() {   
        System.out.println("aaaa");   
        assertEquals(5 + 5, 10);   
    } 
    
    @Test(expected = ArithmeticException.class)   
    public void testExpected() {   
        System.out.println("@Test(expected = Exception.class)");   
        throw new ArithmeticException();   
    }   
    
    @After  
    public void after() {   
        System.out.println("@After");   
    }   
    
    @BeforeClass  
    public static void beforeClass() {   
        System.out.println("@BeforeClass");   
    };   
    
    @AfterClass  
    public static void afterClass() {   
        System.out.println("@AfterClass");   
    };   
};  