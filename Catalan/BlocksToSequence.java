import java.util.*;

public class BlocksToSequence{
  public static ArrayList<Integer> mapToSeq(ArrayList<Integer> pile){
    ArrayList<Integer> ones = new ArrayList<Integer>();
    int prev = 0;
    pile.add(0);  //pit of doom!
    for(int i=0;i<pile.size();i++){
      int slope = pile.get(i)-prev;
      if(slope>=0){
        for(int j=0;j<slope+1;j++){
          ones.add(1);
        }
        ones.add(-1);
      }
      else if(slope==0){
        ones.add(1);
        ones.add(-1);
      }
      else{
        ones.add(-1);
      }
      prev = pile.get(i);
    }
    return ones;
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    ArrayList<Integer> blocks = new ArrayList<Integer>();
    for(int i=0;i<(s.length()+1)/2;i++){
      int val = 2*i;
      char c = s.charAt(val);
      blocks.add(Character.getNumericValue(c));
    }

    System.out.println(mapToSeq(blocks));
  }
}
