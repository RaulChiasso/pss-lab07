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

    public String calculateString(Integer index) {
        String result = ""; 
        if (index.toString().toCharArray()[0] == 3 || index.toString().toCharArray()[1] == 3) {
            fizz();
        }

        if (index % 3 != 0 && index % 5 != 0){
            result += index;
        } else if (index % 3 ==0) {
            result += fizz();
        } if (index % 5 ==0) {
            result +=buzz();
        }
        result += "\n";
        return result;
    }

    private String fizz() {
        return FIZZ;
    }

    private String buzz() {
        return BUZZ;
    }
}
