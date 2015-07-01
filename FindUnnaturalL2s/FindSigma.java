import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FindSigma
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int factorizeMe = sc.nextInt();
		ArrayList<Integer> facts = addWhatWeNeed(oddsIn(factorsOf(factorizeMe)),factorizeMe);
		int rightOne = largestBelow(facts, Math.sqrt(2 * factorizeMe));
		for(int i = 0; i < facts.size(); i++)
		{
			System.out.print(facts.get(i));
			if(i < facts.size() - 1)
			{
				System.out.print(", ");
			}
		}
		System.out.println("\n" + Math.sqrt(factorizeMe));
		System.out.println(rightOne);
		System.out.println((rightOne - 1) / 2d + (factorizeMe / (double)rightOne));
	}
	
	public static ArrayList<Integer> factorsOf(int n)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 1; i <= Math.sqrt(n); i++)
		{
			if(n % i == 0)
			{
				temp.add(i);
			}
		}
		for(int i = temp.size() - 1; i >= 0; i--)
		{
			int other = n / temp.get(i);
			if(other != temp.get(i))
			{
				temp.add(other);
			}
		}
		return temp;
	}
	
	public static ArrayList<Integer> oddsIn(ArrayList<Integer> ints)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(Integer i : ints)
		{
			if(i % 2 == 1)
			{
				temp.add(i);
			}
		}
		return temp;
	}
	
	public static ArrayList<Integer> addWhatWeNeed(ArrayList<Integer> ints, int k)
	{
		int temp = ints.size();
		for(int i = 0; i < temp; i++)
		{
			ints.add(2 * k / ints.get(i));
		}
		return ints;
	}
	
	public static int largestBelow(ArrayList<Integer> ints, double val)
	{
		Collections.sort(ints);
		for(int i = 0; i < ints.size(); i++)
		{
			if(ints.get(i) >= val)
			{
				return(ints.get(i - 1));
			}
		}
		return -1;
	}
	
	public static int sigma(int k)
	{
		return largestBelow(addWhatWeNeed(oddsIn(factorsOf(k)),k), Math.sqrt(2 * k));
	}
	
	public static int l1(int k)
	{
		int rightOne = largestBelow(addWhatWeNeed(oddsIn(factorsOf(k)),k), Math.sqrt(2 * k));
		return (int) ((rightOne - 1) / 2d + (k / (double)rightOne));
	}
}
