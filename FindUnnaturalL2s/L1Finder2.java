import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class L1Finder2
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		/*System.out.print("Choose a mode: \n"
					   + "1: Print L1 for gamma and a range of Ks\n"
					   + "2: Print L2 for range of Ks\n"
					   + "3: Generate a pretty html of L1s for gamma and a range of Ks\n"
					   + "4: Generate a pretty html showing L using shorter of L1 and L2\n"
					   + "5: Generate a pretty html showing L1 using the max prime of numbers in a range\n"
					   + "6: Generate a csv showing L1 or L2 odd gammas and 2^Ks\n"
					   + "7: Generate a csv showing whether L2 values are natural\n"
					   + "8: Check our guess\n"
					   + "9: Print number of T2s between two numbers\n"
					   + "Choice: ");*/
		int mode = 9;
		int gamma;
		int maxK;
		int minVal, maxVal;
		switch(mode)
		{
		case 1:
			System.out.print("Input a gamma: ");
			 gamma = sc.nextInt();
			System.out.print("Input a maximum k: ");
			maxK = sc.nextInt();
			printL1ForKs(gamma,maxK);
			break;
		case 2:
			System.out.println("Input a max value: ");
			maxVal = sc.nextInt();
			for(int i = 1; i <= maxVal; i++)
			{
				System.out.println(findL2(i));
			}
			break;
		case 3:
			System.out.print("Input a gamma: ");
			gamma = sc.nextInt();
			System.out.print("Input a maximum k: ");
			maxK = sc.nextInt();
			generatePrettyL1s(gamma,maxK);
			break;
		case 4:
			System.out.print("Input a gamma: ");
			gamma = sc.nextInt();
			System.out.print("Input a maximum k: ");
			maxK = sc.nextInt();
			compareL1L2(gamma,maxK);
			break;
		case 5:
			System.out.print("Input a max value: ");
			maxVal = sc.nextInt();
			maxPrimeK(maxVal);
			break;
		case 6:
			System.out.print("Input a max gamma: ");
			gamma = sc.nextInt();
			System.out.print("Input a max k: ");
			maxK = sc.nextInt();
			compareL1L2Csv(gamma,maxK);
			break;
		case 7:
			System.out.print("Input a max value: ");
			maxVal = sc.nextInt();
			unnaturalOrNatural(maxVal);
			break;
		case 8:
			System.out.print("Input a max value: ");
			maxVal = sc.nextInt();
			checkOurGuess(maxVal);
			break;
		case 9:
			//System.out.print("Input a min value: ");
			minVal = Integer.parseInt(args[0]); //sc.nextInt();
			//System.out.print("Input a max value: ");
			maxVal = Integer.parseInt(args[1]); //sc.nextInt();
			numOfU2LessThan(minVal,maxVal);
			break;
		default:
			System.out.println("CAN YOU EVEN READ?\nactually though you probably forgot a break statement...");
		}
		
		sc.close();
	}
	
	public static double findL1(int gamma, int k, int skipLevel)
	{
		return (skipLevel * gamma - 1) / 2.0 + k / (double) skipLevel;
	}
	
	public static int omega(int n)
	{
		for(int i = (int) Math.sqrt(n); i >= 1; i--)
		{
			if(n % i == 0)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int findL2(int n)
	{
		for(int i = (int) Math.sqrt(n); i >= 1; i--)
		{
			if(n % i == 0)
			{
				return i + (n / i);
			}
		}
		return -1;
	}
	
	public static double findMaxN(int gamma, int k)
	{
		return 2*k/(double)gamma;
	}
	
	public static void printL1ForKs(int gamma, int maxK) //prints L1 Forks  o--E
	{
		for(int k = 1; k <= maxK; k++)
		{
			int skipLevel = 1;
			double minL1 = Double.MAX_VALUE;
			while(skipLevel < findMaxN(gamma, k) + 1)
			{
				double tempL1 = findL1(gamma, k, skipLevel);
				if(tempL1 < minL1 && tempL1 == Math.floor(tempL1))
				{
					minL1 = tempL1;
				}
				skipLevel++;
			}
			System.out.println("k: " + k + ", L1: " + minL1);
		}
	}
	
	public static int findL1ForK(int k)
	{
		int gamma = 1;
		int skipLevel = 1;
		double minL1 = Double.MAX_VALUE;
		while(skipLevel < findMaxN(gamma, k) + 1)
		{
			double tempL1 = findL1(gamma, k, skipLevel);
			if(tempL1 < minL1 && tempL1 == Math.floor(tempL1))
			{
				minL1 = tempL1;
			}
			skipLevel++;
		}
		return (int) minL1;
	}
	
	public static void generatePrettyL1s(int gamma, int maxK)
	{
		try
		{
			PrintWriter p = new PrintWriter(new File("L1sOfGamma=" + gamma + ".htm"));
			p.println("<!DOCTYPE html>");
			p.println("<link href='http://fonts.googleapis.com/css?family=Permanent+Marker' rel='stylesheet' type='text/css'>");

			p.println("<link href='data/styles.css' rel='stylesheet' type='text/css'>");
			p.println("<body>");
			p.println("<h1>Gamma = " + gamma +"</h1>");
			for(int k = 1; k <= maxK; k++)
			{
				int skipLevel = 1;
				double minL1 = Double.MAX_VALUE;
				int minN = 0;
				while(skipLevel < findMaxN(gamma, k) + 1)
				{
					double tempL1 = findL1(gamma, k, skipLevel);
					if(tempL1 < minL1 && tempL1 == Math.floor(tempL1))
					{
						minL1 = tempL1;
						minN = skipLevel;
					}
					skipLevel++;
				}
				int l2 = findL2(k * gamma);
				switch(minN)
				{
				case 1:
					p.println("<font color='blue'>*</font>k: " + k + ", L1: " + (int) minL1 + " n: " + minN + "  L2: " + l2 + "<br />");
					break;
				case 2:
					p.println("<font color='red'>k: " + k + ", L1: " + (int) minL1 + " n: " + minN + "  L2: " + l2 + "</font><br />");
					break;
				case 3:
					p.println("<font color='green'> k: " + k + ", L1: " + (int) minL1 + " n: " + minN + "  L2: " + l2 + "</font><br />");
					break;
				case 4:
					p.println("<font color='blue'> k: " + k + ", L1: " + (int) minL1 + " n: " + minN + "  L2: " + l2 + "</font><br />");
					break;
				default:
					p.println(" k: " + k + ", L1: " + (int) minL1 + " n: " + minN + "  L2: " + l2 + "<br />");
				}
				
			}
			p.println("</body>");
			p.close();
		}
		catch(Exception e)
		{
			System.out.println("meh");
		}
	}
	
	public static void numOfT2LessThan(int minN, int maxN)
	{
		int num = 0;
		
		int n = minN;
		if(n % 2 == 1)
		{
			n++;
		}
		while(n <= maxN)
		{
			int l1 = findL1ForK(n);
			int l2 = findL2(n);
			if(l2 < l1)
			{
				num++;
			}
			n+=2;
		}
		System.out.println(num);
	}
	
	public static void numOfU2LessThan(int minN, int maxN)
	{
		int num = 0;
		int n = minN;
		if(n % 2 == 1)
		{
			n++;
		}
		while(n <= maxN)
		{
			int gamma = n;
			int k = 0;
			while(gamma % 2 == 0)
			{
				gamma /= 2;
				k += 1;
			}
			int l1 = findL1ForK(n);
			int l2 = findL2(n);
			boolean isNatural = (Xnauts.tableEntry(k, gamma) > 0);
			if(l2 < l1 && !isNatural)
			{
				num++;
			}
			n+=2;
		}
		System.out.println(num);
	}
	
	public static void unnaturalOrNatural(int maxN)
	{
		try
		{
			PrintWriter p = new PrintWriter(new File("NaturalorUnnatural.csv"));
			int gamma = 1;
			int k = 2;
			p.println("gamma,x,prime factorization,gamma * 2 ^ k, omega, sigma, l1, l2");
			while((Math.pow(2,k) * gamma) < maxN)
			{
				while((Math.pow(2,k) * gamma) < maxN)
				{
					if(Math.pow(2, k) * gamma < maxN)
					{
						int l1 = findL1ForK((int) (Math.pow(2,k) * gamma));
						int l2 = findL2((int) (Math.pow(2,k) * gamma));
						int omg = omega((int) (Math.pow(2,k) * gamma));
						int sig = FindSigma.sigma((int) (Math.pow(2,k) * gamma));
						ArrayList<BigInteger> primeFacts = PrimeFact.primeDecomp(BigInteger.valueOf((int) (Math.pow(2,k) * gamma)));
						boolean isNatural = Xnauts.tableEntry(k, gamma) > 0;
						
						p.print(gamma + "," + k + ",(");
						
						for(int i = 0; i < primeFacts.size(); i++)
						{
							p.print(primeFacts.get(i));
							if(i < primeFacts.size() - 1)
							{
								p.print(" * ");
							}
						}
						p.print(")," + ((int) Math.pow(2,k) * gamma) + "," + omg + "," + sig + "," + l1 + "," + l2);
						
						if(l1 < l2)
						{
							p.println(",T1");
						}
						else if(l2 < l1)
						{
							if(isNatural)
							{
								p.println(",N2"); //natural type 2
							}
							else
							{
								p.println(",U2"); //unnatural type 2
							}
						}
						else
						{
							p.println(",T3"); //the same don't care?
						}
					}
					k++;
				}
				k = 2;
				gamma += 2;
			}
			p.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void checkOurGuess(int maxN)
	{
		try
		{
			PrintWriter p = new PrintWriter(new File("CheckOurGuess.csv"));
			int gamma = 1;
			int k = 1;
			while((Math.pow(2,k) * gamma) < maxN)
			{
				while((Math.pow(2,k) * gamma) < maxN)
				{
					if(Math.pow(2, k) * gamma < maxN)
					{
						int l1 = findL1ForK((int) (Math.pow(2,k) * gamma));
						int l2 = findL2((int) (Math.pow(2,k) * gamma));
						int omg = omega((int) (Math.pow(2,k) * gamma));
						int sig = FindSigma.sigma((int) (Math.pow(2,k) * gamma));
						ArrayList<BigInteger> primeFacts = PrimeFact.primeDecomp(BigInteger.valueOf((int) (Math.pow(2,k) * gamma)));
						int p2 = primeFacts.get(primeFacts.size()-1).intValue();
						int r = (int) (Math.pow(2, k-2)*gamma/p2);
						
						boolean isNatural = Xnauts.tableEntry(k, gamma) > 0;
						
						if (r <= p2 && p2 <= 2*r && r % 2 == 1) {
							if (sig >= omg) {
								for(int i = 0; i < primeFacts.size(); i++)
								{
									p.print(primeFacts.get(i));
									if(i < primeFacts.size() - 1)
									{
										p.print(" * ");
									}
								}
							p.println(", "+sig+", "+omg);
//							for(int i = 0; i < primeFacts.size(); i++)
//							{
//								p.print(primeFacts.get(i));
//								if(i < primeFacts.size() - 1)
//								{
//									p.print(" * ");
//								}
//							}
//							p.println(", "+sig+", "+omg);
							}
						}
						if(l1 < l2)
						{
							//type 1 don't care

						}
						else if(l2 < l1)
						{
							if(isNatural)
							{
								//natural type 2
							}
							else
							{
								//unnatural type 2
							}
						}
						else
						{
							//the same
						}
					}
					k++;
				}
				k = 1;
				gamma += 2;
			}
			p.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void compareL1L2Csv(int maxGamma, int maxK)
	{
		try
		{
			PrintWriter p = new PrintWriter(new File("L1orL2MaxGamma.csv"));
			for(int gamma = -1; gamma <= maxGamma; gamma+=2)
			{
				for(int k = 0; k < maxK; k++)
				{
					if(gamma <0)
					{
						p.print(k);
					}
					else
					{
						if(k == 0)
						{
							p.print(gamma);
						}
						else
						{
							int l1 = findL1ForK((int) (Math.pow(2,k) * gamma));
							int l2 = findL2((int) (Math.pow(2,k) * gamma));
							int sigma = FindSigma.sigma((int) (Math.pow(2, k)*gamma));
							int omega = omega((int) (Math.pow(2, k)*gamma));
							
							if(omega > sigma)
							{
								p.print("*");
							}
							else if(omega == sigma)
							{
								p.print("#");
							}
							else
							{
								p.print("@");
							}
							if(l1 < l2)
							{
								p.print("(1) " + sigma + " : " + omega);
							}
							else if(l2 < l1)
							{
								p.print("(2) " + sigma + " : " + omega);
							}
							else
							{
								p.print("(3) " + sigma + " : " + omega);
							}
						}
					}
					if(k < maxK - 1)
					{
						p.print(",");
					}
				}
				p.println();
			}
			p.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void compareL1L2(int gamma, int maxK)
	{
		try
		{
			PrintWriter p = new PrintWriter(new File("ComparisonOfGamma=" + gamma + ".htm"));
			p.println("<!DOCTYPE html>");
			p.println("<link href='http://fonts.googleapis.com/css?family=Permanent+Marker' rel='stylesheet' type='text/css'>");

			p.println("<link href='data/styles.css' rel='stylesheet' type='text/css'>");
			p.println("<body>");
			p.println("<h1>Gamma = " + gamma +"</h1>");
			for(int k = 1; k <= maxK; k++)
			{
				int skipLevel = 1;
				double minL1 = Double.MAX_VALUE;
				int minN = 0;
				while(skipLevel < findMaxN(gamma, k) + 1)
				{
					double tempL1 = findL1(gamma, k, skipLevel);
					if(tempL1 < minL1 && tempL1 == Math.floor(tempL1))
					{
						minL1 = tempL1;
						minN = skipLevel;
					}
					skipLevel++;
				}
				int l2 = findL2(k * gamma);
				
				if(minL1 > l2)
				{
					p.println("<font color='red'>k: " + k + " L: " + l2 +" (Type 2)</font><br />");
				}
				else if(minL1 == l2)
				{
					p.println("<font color='black'>k: " + k + " L: " + l2 +" (Type 1 and 2) n: " + minN + "</font><br />");
				}
				else
				{
					p.println("<font color='green'>k: " + k + " L: " + (int) minL1 +" (Type 1) n: " + minN + "</font><br />");
				}
			}
			p.println("</body>");
			p.close();
		}
		catch(Exception e)
		{
			System.out.println("meh");
		}
	}
	public static void maxPrimeK(int maxVal)
	{
		try
		{
			PrintWriter p = new PrintWriter(new File("MaxPrimeUpTo" + maxVal + ".htm"));
			p.println("<!DOCTYPE html>");
			p.println("<link href='http://fonts.googleapis.com/css?family=Permanent+Marker' rel='stylesheet' type='text/css'>");

			p.println("<link href='data/styles.css' rel='stylesheet' type='text/css'>");
			p.println("<body>");
			p.println("<h1>Max Prime up to " + maxVal + "</h1>");
			for(int val = 1; val <= maxVal; val++)
			{
				int gamma = maxPrimeFactor(val);
				int k = val / gamma;
				int skipLevel = 1;
				double minL1 = Double.MAX_VALUE;
				int minN = 0;
				while(skipLevel < findMaxN(gamma, k) + 1)
				{
					double tempL1 = findL1(gamma, k, skipLevel);
					if(tempL1 < minL1 && tempL1 == Math.floor(tempL1))
					{
						minL1 = tempL1;
						minN = skipLevel;
					}
					skipLevel++;
				}
				
				switch(gamma)
				{
				case 1:
					p.print("<font color='purple'>");
					break;
				case 3:
					p.print("<font color='palegreen'>");
					break;
				case 5:
					p.print("<font color='orange'>");
					break;
				case 7:
					p.print("<font color='red'>");
					break;
				case 11:
					p.print("<font color='blue'>");
					break;
				case 13:
					p.print("<font color='lime'>");
					break;
				case 17:
					p.print("<font color='cyan'>");
					break;
				case 19:
					p.print("<font color='magenta'>");
					break;
				case 23:
					p.print("<font color='yellow'>");
					break;
				case 29:
					p.print("<font color='mediumslateblue'>");
					break;
				case 31:
					p.print("<font color='seagreen'>");
					break;
				case 37:
					p.print("<font color='tan'>");
					break;
				case 41:
					p.print("<font color='maroon'>");
					break;
				default:
					p.print("<font color='black'>");
					break;
				}
				p.println("Val: " + val + " Gamma: " + gamma + " k: " + k + " n: " + minN + " L1: " + (int) minL1 + "</font><br />");
			}
			p.println("</body>");
			p.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static int maxPrimeFactor(int n)
	{
		if(n == 1)
		{
			return 1;
		}
		try
		{
			int curPrime = 0;
			int maxPrimeFactor = 0;
			Scanner sc = new Scanner(new File("C:\\Users\\Robbie\\OneDrive\\workspace2\\Sandpile\\data\\PRIMES1C.TXT"));
			do
			{
				curPrime = sc.nextInt();
				if(n % curPrime == 0)
				{
					maxPrimeFactor = curPrime;
				}
			}
			while(curPrime < n && sc.hasNextInt());
			sc.close();
			if(maxPrimeFactor == 2)
			{
				return 1;
			}
			else
			{
				return maxPrimeFactor;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}