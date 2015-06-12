import java.util.*;

public class SequenceToBlocks{
  public static ArrayList<Integer> mapToBlocks(String[] ones){
    int runningSum = 0;
    ArrayList<Integer> blocks = new ArrayList<Integer>();
    for(int i=0;i<ones.length;i++){
      runningSum+=Integer.parseInt(ones[i]);
      if(Integer.parseInt(ones[i])==-1){
        blocks.add(runningSum);
      }
    }
    blocks.remove(blocks.size()-1);
    return blocks;
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    String[] split = s.split(",");
    System.out.println(mapToBlocks(split));
  }
}
