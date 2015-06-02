import java.util.ArrayList;
public class SmallLength{
  public static ArrayList<Integer> sumTest(int n){
    int shortestSum = n;
    int start = 0;
    int stop = 0;
    int sum = 0;
    for(int i=1;i<n;i++){
      for(int j=1;j<=i;j++){
        for(int k=j;k<=i;k++){
          if((j+k)*(k-j+1)/2==n){
            shortestSum=i;
            start = j;
            stop = k;
            break;
          }
        }
        if(shortestSum<n){
          break;
        }
      }
      if(shortestSum<n){
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
  public static void main(String[] args){
    int test = Integer.parseInt(args[0]);
    if(prodTest(test).get(0)>sumTest(test).get(0)){
      System.out.println("The shortest length is "+sumTest(test).get(0)+" with a sum starting at "+sumTest(test).get(1)+" and ending at "+sumTest(test).get(2)+".");
    }
    else if(prodTest(test).get(1)==0){
      System.out.println("The shortest length is "+test+".");
    }
    else{
      System.out.println("The shortest length is "+prodTest(test).get(0)+" with a product of "+prodTest(test).get(1)+"x"+prodTest(test).get(2)+".");
    }
  }
}
