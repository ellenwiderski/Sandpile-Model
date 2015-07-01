import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Xnauts
{
	public static void main(String[] args)
	{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Input gamma: ");
//		int gamma = sc.nextInt();
//		System.out.println("Input x");
//		int x = sc.nextInt();
//		System.out.println("sNaught = " + sNaught(gamma) + " xNaught = " + xNaught(gamma));
//		System.out.println("("+x+", "+gamma+") = "+tableEntry(x,gamma));
//		sc.close();
		writeTable(1000,25);
	}
	
	public static int sNaught(int gamma)
	{
		return xNaught(gamma) - (int) Math.ceil((xNaught(gamma) - 2) - Math.log(gamma) / Math.log(2));
	}
	
	public static int xNaught(int gamma)
	{
		return (int) Math.ceil(Math.log(3*gamma+1)/Math.log(2));
	}
	
	public static int tableEntry(int x, int gamma)
	{
		int out = (int) Math.floor(((x - sNaught(gamma)) / 2.0)) + 1;
		if(out > 0)
		{
			return out;
		}
		else return 0;
	}
	
	public static void writeTable(int maxGamma, int maxX)
	{
		try
		{
			PrintWriter output = new PrintWriter(new File("output.csv"));
			for(int gamma = -1; gamma < maxGamma; gamma += 2)
			{
				if(gamma > 0)
				{
					output.print(gamma + ",");
					for(int x = 1; x < maxX; x++)
					{
						output.print(tableEntry(x, gamma));
						if(x < maxX - 1)
						{
							output.print(",");
						}
					}
					output.print("\n");
				}
				else
				{
					for(int x = 1; x < maxX; x++)
					{
						output.print("," + x);
					}
					output.println();
				}
			}
			output.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void writeTablePrimes(int numGammas, int maxX)
	{
		int[] primesList = new int[numGammas];
		try
		{
			PrintWriter output = new PrintWriter(new File("output.csv"));
			Scanner primes = new Scanner(new File("C:/Users/Robbie/OneDrive/workspace2/Sandpile/data/PRIMES1C.TXT"));
			int i = 0;
			while(i < numGammas && primes.hasNextInt())
			{
				primesList[i] = primes.nextInt();
				i++;
			}
			primes.close();
			for(int gammaidx = -1; gammaidx < primesList.length; gammaidx++)
			{
				if(gammaidx >=0)
				{
					output.print(primesList[gammaidx] + ",");
					for(int x = 1; x < maxX; x++)
					{
						output.print(tableEntry(x, primesList[gammaidx]));
						if(x < maxX - 1)
						{
							output.print(",");
						}
					}
					output.print("\n");
				}
				else
				{
					for(int x = 1; x < maxX; x++)
					{
						output.print("," + x);
					}
					output.println();
				}
			}
			output.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}