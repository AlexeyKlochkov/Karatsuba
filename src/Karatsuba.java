import java.util.NoSuchElementException;

import static java.lang.Math.log10;
import static java.lang.Math.pow;

/**
 * Created by alex on 10/6/15.
 * Karatsuba's multiplication algorithm
 * input: 2 numbers
 * output: product of those numbers
 *
 * The idea of algorithms is to imagine both numbers as
 * x = (10 ^ (n/2)) * a + b
 * y = (10 ^ (n/2)) * c + d
 * then calculate the multiplication as
 * x * y = 10^n * a*c + 10^(n/2) * (a*d + b*c) + b*d
 * and use Gauss's trick for second summand as
 * (a + b)*(c + d) - a*c - b*d = a*d + b*c
 * and counts only 3 products instead of 4
 * calculate production recursively
 */

public class Karatsuba {

    public static int multiply(Integer x, Integer y) {
        if (x == null || y == null) throw new NoSuchElementException();
        if (x > 10 || y > 10) {
            int n;
            if (x > y)
                n = (int)(log10(x) + 1);
            else
                n = (int)(log10(y) + 1);
            if (n % 2 == 1) {
                n--;
            }
            int a = (int)(x / pow(10, n / 2));
            int b = x - (int)(a * pow(10, n / 2));
            int c = (int)(y / pow(10, n / 2));
            int d = y - (int)(c * pow(10, n / 2));
            int p = multiply(a, c);
            int q = multiply(b, d);
            int r = multiply(a + b, c + d) - p - q;
            return (int) (pow(10, n) * p + (pow(10, n / 2) * r) + q);
        }
        else return x * y;
    }

    public static void main(String[] args) {
        int k =  Karatsuba.multiply(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.print("Product of " + args[0] + " and "+ args[1] +" is: " + k);
    }
}

