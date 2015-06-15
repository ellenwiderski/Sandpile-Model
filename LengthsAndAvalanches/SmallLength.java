import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

public class SmallLength{
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
  public static void startValuePrimes(int n){  //prints start values for primes up to n
    for(int i=2;i<=n;i++){
      ArrayList<Integer> primes = primeFactors(i);
      ArrayList<Integer> prod = prodTest(i);
      ArrayList<Integer> sum = sumTest(i);
      if(primes.get(primes.size()-1)==i){
        if(prod.get(0)>sum.get(0)){
          System.out.println(sum.get(0)+" s "+sum.get(1)+" "+sum.get(2)+" Primes: "+primes+" "+(i+1)/2);
        }
        else if(prod.get(1)==0){
          System.out.println(i+" Primes: "+primes);
        }
        else{
          System.out.println(prod.get(0)+" p "+prod.get(1)+" "+prod.get(2)+" Primes: "+primes+" "+(i+1)/2);
        }
      }
    }
  }
  public static void testPrimesIncrementProperty(int n){  //test prime increment property for given prime n
    try{
      PrintWriter writer = new PrintWriter("PrimeIncrements"+n+".txt","UTF-8");
      writer.println("g k start end length");
      for(int i=n+2;i<=10*n;i++){
        ArrayList<Integer> sum = sumTest(i*n);
        ArrayList<Integer> sumList = new ArrayList<Integer>();
        for(int j=sum.get(1);j<=sum.get(2);j++){
          sumList.add(j);
        }
        writer.println(n +" " + i + " "+sumList.get(0)+" "+sum.get(0)+" "+(sum.get(0)-sumList.get(0)+1));
      }
      writer.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }

  }
  public static void indValues(int n){  //spits out value for given input
    ArrayList<Integer> prod = prodTest(n);
    ArrayList<Integer> sum = sumTest(n);
    if(prod.get(0)>sum.get(0)){
      System.out.println(sum.get(0)+" s "+sum.get(1)+" "+sum.get(2)+" Primes: "+primeFactors(n));
    }
    else if(prod.get(1)==0){
      System.out.println(n+" Primes: "+primeFactors(n));
    }
    else{
      System.out.println(prod.get(0)+" p "+prod.get(1)+" "+prod.get(2)+" Primes: "+primeFactors(n));
    }
  }
  public static void writeFile(int n){  //writes out values for inputs up to and including n
    int sSum=0;
    int pSum=0;
    try{
      PrintWriter writer = new PrintWriter("AvalancheSizeData.txt","UTF-8");
      for(int i=1;i<=n;i++){
        ArrayList<Integer> prod = prodTest(i);
        ArrayList<Integer> sum = sumTest(i);
        if(prod.get(0)>sum.get(0)){
          writer.println(i+" "+sum.get(0)+" s "+sum.get(1)+" "+sum.get(2)+" Primes: "+primeFactors(i));
          sSum++;
        }
        else if(prod.get(1)==0){
          writer.println(i+" Primes: "+primeFactors(i));
        }
        else{
          writer.println(i+" "+prod.get(0)+" p "+prod.get(1)+" "+prod.get(2)+" Primes: "+primeFactors(i));
          pSum++;
        }
      }
      writer.println("Total s's: "+sSum+"     Total p's: "+pSum+"     Total else: "+(n-pSum-sSum));
      writer.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  public static void writeFileType2Only(int n){  //writes out values for inputs up to n that yield type 2
    try{
      PrintWriter writer = new PrintWriter("Type2Data.txt","UTF-8");
      for(int i=1;i<=n;i++){
        ArrayList<Integer> prod = prodTest(i);
        ArrayList<Integer> sum = sumTest(i);
        if(prod.get(0)<sum.get(0)){
          writer.println(i+" "+prod.get(1)+" "+prod.get(2)+" Primes: "+primeFactors(i));
        }
      }
      writer.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  public static void ellensfunk(int k) {
    try {
      int [] primes = {5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199};
      PrintWriter writer = new PrintWriter("kIs"+k+".txt","UTF-8");
      ArrayList<Integer> kfactors = primeFactors(k);
      writer.println(kfactors);
      writer.println("g start end len");
      for (int g = 3; g < k-2; g++) {
        ArrayList<Integer> gfactors = primeFactors(g);
        if (gfactors.get(gfactors.size()-1) == g) {
          if (kfactors.get(kfactors.size()-1) <= g) {
            ArrayList<Integer> sum = sumTest(k*g);
            ArrayList<Integer> sumList = new ArrayList<Integer>();
            for(int j=sum.get(1);j<=sum.get(2);j++){
              sumList.add(j);
            }
            int length = sum.get(0) - sumList.get(0) + 1;
            writer.println(g+" "+sumList.get(0)+" "+sum.get(0)+" "+length+" "+(primeFactors(length)));
          }
        }
      }
      writer.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int test = sc.nextInt();
    ellensfunk(test);
  }
}
