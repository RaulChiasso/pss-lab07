package it.unibo.a;

public class FizzBuzz {
    final static String FIZZ = "Fizz";
    final static String BUZZ = "Buzz";

    public FizzBuzz(int a) {
        for (int i = 1; i < 101; i ++) {
            System.out.print(calculateString(i));
        }        
    }

    public FizzBuzz() {
    }

    public String calculateString(int index) {
        String result = ""; 
        if (index % 3 != 0 && index % 5 != 0){
            result += index;
        } else if (index % 3 ==0) {
            result += Fizz();
        } if (index % 5 ==0) {
            result +=Buzz();
        }
        result += "\n";
        return result;
    }

    private String Fizz() {
        return FIZZ;
    }

    private String Buzz() {
        return BUZZ;
    }
}
