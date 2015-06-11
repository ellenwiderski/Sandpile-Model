import java.util.ArrayList;
import java.io.PrintWriter;

public class TestSmallLength{
  public static ArrayList<Integer> primeFactors(int n){
    ArrayList<Integer> primes = new ArrayList<Integer>();
    int test = n;
    int start;
    while(test!=1){
      if(test==n){
        start = 2;
      }
      else{
        start = primes.get(primes.size()-1);
      }
      for(int i=start;i<=test;i++){
        if(test%i==0){
          primes.add(i);
          test/=i;
          break;
        }
      }
    }
    return primes;
  }
  public static ArrayList<Integer> sumTest(int n){
    int shortestSum = n;
    int start = 0;
    int stop = 0;
    int i = 1;
    int j = 1;
    while(shortestSum==n){
      if((j+i)*(i-j+1)/2==n){
        shortestSum = i;
        start = j;
        stop = i;
      }
      else{
        if(j==i){
          i++;
          j=1;
        }
        else{
          j++;
        }
      }
      if(i==n){
        break;
      }
    }
    ArrayList<Integer> values = new ArrayList<Integer>();
    values.add(shortestSum);
    values.add(start);
    values.add(stop);
    return values;
  }
  public static ArrayList<Integer> prodTest(int n){
    int shortestProd = n;
    int first = 0;
    int second = 0;
    for(int i=1;i<n;i++){
      for(int j=1;j<=(i/2.0);j++){
        if(j*(i-j)==n){
          shortestProd = i;
          first = j;
          second = i-j;
          break;
        }
      }
      if(shortestProd<n){
        break;
      }
    }
    ArrayList<Integer> values = new ArrayList<Integer>();
    values.add(shortestProd);
    values.add(first);
    values.add(second);
    return values;
  }
  public static int upperB(int g) {
    ArrayList<Integer> primes = primeFactors(g);
    int m = primes.get(primes.size()-1);
    return (int)(Math.pow(m,2)-m+2*g)/(2*m);
  }

  public static int lilB(int g) {
    ArrayList<Integer> primes = primeFactors(g);
    int m = primes.get(primes.size()-1);
    return (int)((-1+Math.sqrt(8*g+1))/2);
  }

  public static void main(String[] args){
    int test = Integer.parseInt(args[0]);
    if(prodTest(test).get(0)>sumTest(test).get(0)){     //test individual values
      System.out.println("Lower: "+lilB(test)+" Actual: "+sumTest(test).get(0)+" Upper: "+upperB(test));
    }
    else if(prodTest(test).get(1)==0){
      System.out.println("Lower: "+lilB(test)+" Actual: "+test+" Upper: "+upperB(test));

    }
    else{
      System.out.println("Lower: "+lilB(test)+" Actual: "+prodTest(test).get(0)+" Upper: "+upperB(test));
    }
  }
}
