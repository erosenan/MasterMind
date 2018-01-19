package gali;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		int rng = 0;
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		
		//range of digits
		while(rng > 9 || rng < 4)
		{
		System.out.println("At what range of digits go you want to play?");
		rng = sc.nextInt();
		}
		
		//numbers chosen by computer
		int a = rnd.nextInt(rng) + 1, b = rnd.nextInt(rng) + 1, c = rnd.nextInt(rng) + 1, d = rnd.nextInt(rng) + 1;
		
		while (a == b)
			b = rnd.nextInt(rng) + 1;
		
		while (a == c || b == c)
			c = rnd.nextInt(rng) + 1;
		
		while (a == d || b == d || c == d)
			d = rnd.nextInt(rng) + 1;
		
		//numbers given by user
		int ag = 0, bg = 0, cg = 0, dg = 0, abcdg;
		
		//times numbers were correct/wrong
		int num = 0, plc = 0, wrng;
		
		//times tried
		int trs = 0;
		
		System.out.print("You have to enter 4 digits between 1-");
		System.out.print(rng);
		System.out.println(" without space.");
		
		while (true)
		{
			abcdg = 0;
			
			//receive digits 
			while (ag > rng || bg > rng || cg > rng || abcdg < 1111)
			{
				System.out.print("Enter your try:");
				abcdg = sc.nextInt();
				
				//separate digits
				ag = abcdg / 1000;
				bg = abcdg / 100 - ag * 10;
				dg = abcdg % 10;
				cg = (abcdg % 100 - dg) / 10;
			}
			
			//first digit check
			plc = plc + DgtCkP(a, ag);
			num = num + DgtCkN(a, bg, cg, dg);
			
			//second digit check
			plc = plc + DgtCkP(b, bg);
			num = num + DgtCkN(b, ag, cg, dg);
			
			//third digit check
			plc = plc + DgtCkP(c, cg);
			num = num + DgtCkN(c, bg, ag, dg);
			
			//forth digit check
			plc = plc + DgtCkP(d, dg);
			num = num + DgtCkN(d, bg, cg, ag);
			
			trs++;
			
			//result printing
			if (plc == 4)
			{
				System.out.print("You beat the game after ");
				System.out.print(trs);
				System.out.print(" tries!");
				break;
			}
			else
			{
				System.out.print("Your result is:");
				wrng = 4 - plc - num;
				while(plc > 0)
				{
					System.out.print("!");
					plc --;
				}
				
				while(num > 0)
				{
					System.out.print("~");
					num --;
				}
				while(wrng > 0)
				{
					System.out.print("X");
					wrng --;
				}
				System.out.println();
			}
		}
	}
	
	//check at correct place
	private static int DgtCkP(int gvn, int crct)
	{
		if (crct == gvn)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	//check at wrong place
	private static int DgtCkN(int ms, int gvnA, int gvnB, int gvnC)
	{
		if (ms == gvnA || ms == gvnB || ms == gvnC)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}