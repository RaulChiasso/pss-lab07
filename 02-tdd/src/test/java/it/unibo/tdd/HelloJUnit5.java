package it.unibo.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import it.unibo.a.FizzBuzz;

public class HelloJUnit5 {
    private FizzBuzz fizzbuzz;

    @BeforeEach
    public void setUp() { 
        this.fizzbuzz = new FizzBuzz();
    }

    @Test
    public void testFizzBuzz() {
        Assertions.assertEquals(fizzbuzz.calculateString(1), "1" + "\n");
        Assertions.assertEquals(fizzbuzz.calculateString(15), "FizzBuzz" + "\n");
        Assertions.assertEquals(fizzbuzz.calculateString(100), "Buzz" + "\n");
    }

    @Test
    public void testFizz() {
        Assertions.assertEquals(fizzbuzz.calculateString(3), "Fizz" + "\n");
        Assertions.assertEquals(fizzbuzz.calculateString(12), "Fizz" + "\n");
        Assertions.assertEquals(fizzbuzz.calculateString(99), "Fizz" + "\n");
    }

    @Test
    public void testBuzz() {
        Assertions.assertEquals(fizzbuzz.calculateString(5), "Buzz" + "\n");
        Assertions.assertEquals(fizzbuzz.calculateString(20), "Buzz" + "\n");
        Assertions.assertEquals(fizzbuzz.calculateString(90), "FizzBuzz" + "\n");
    }
    
    @AfterEach
    public void tearDown() { 
        this.fizzbuzz = new FizzBuzz(0);
    }  
}
