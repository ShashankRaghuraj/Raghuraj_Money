//Money.java
//Shashank Raghuraj
//This simple class consists of only 2 instance variables, containing dollars and cents values.
//It is both incomplete and incorrect.
//Your task:  Complete, fix it, and thoroughly test it in the MoneyRunner file (Main).

import java.util.*;
import java.math.*;
public class Money 
{
	// private instance variables
	private int myDollars;
	private int myCents;
	
	// default constructor method -- no parameters
	public Money()
	{
		myDollars = 0;
		myCents = 0;
	}
	// constructor method to input dollars and cents
	public Money(int newDollars, int newCents)
	{
		myDollars = newDollars;
		myCents = newCents;
    this.normalize();
	}

	//Accessor methods -- return the private variable values
	public int getDollars()
	{
		return myDollars;
	}
	
	public int getCents()
	{
		return myCents;
	}
	
	//  One Money parameter add method
	//  This method combines the totals in this and the parameter amount
	public Money add(Money amount)
	{
		Money result = new Money();
		
		result.myCents = this.myCents + amount.myCents;
		result.myDollars = this.myDollars + amount.myDollars;
		
		result.normalize();
		return result;
	}
	// 	Two int parameter add method  -- YOU WILL ADD THIS AND MUCH MORE!!!
	//  This method gets passed dollars and cents instead of a Money object
  public Money add(int d, int c)
  {
    //adds objects cents and parameter cents, as well as object's dollars and parameter dollars
    Money totalMoney = new Money();
    totalMoney.myCents = this.myCents + c;
    totalMoney.myDollars = this.myDollars + d;
    totalMoney.normalize();
    return totalMoney;
  }
  //subtract method with Money m parameter
  public Money subtract(Money m)
  {
    //subtracts object's cents and parameter object's cents as well as the dollars amounts
    Money totalMoney = new Money();
    totalMoney.myCents =this.myCents - m.myCents;
    totalMoney.myDollars = this.myDollars - m.myDollars;
    totalMoney.normalize();
    return totalMoney;
  }
  // subtract method with Dollars and cents as parameters
  public Money subtract(int dollars, int cents)
  {
    Money totalMoney = new Money();
    //Subtracts cents from object, and parameters listed
    totalMoney.myCents = this.myCents - cents;
    totalMoney.myDollars = this.myDollars - dollars;
    totalMoney.normalize();
    return totalMoney;
  }
  //multiply method with 1 parameter
  public Money multiply(int f)
  {
    //Multiplies both the dollars amount as well as the cents amount by a specific number(f)
    Money totalMoney = new Money();
    totalMoney.myDollars = this.myDollars*f;
    totalMoney.myCents = this.myCents*f;
    totalMoney.normalize();
    return totalMoney; 
  }
  //Sort money method
  public static void SortMoney()
  {
    Scanner reader = new Scanner(System.in);
    BigDecimal temp;

    //creates one money arrays
    System.out.print("How many money values would you like to input?(values cannot be greater than 100)\n> ");
    int input = reader.nextInt();
    Money[] originalArr = new Money[input];
    int maxDollars = 0;
    int maxCents = 0;
    for(int i = 0; i < input; i++)
    {
      Money cellMoney = new Money();
      System.out.print("Enter dollar amount of money object " + (i+1) + ": ");
      int dollarsValue = reader.nextInt();
      cellMoney.myDollars = dollarsValue;
      System.out.print("Enter cents amount of money object " + (i+1) + ": ");
      int centsValue = reader.nextInt();
      cellMoney.myCents = centsValue;
      cellMoney.normalize();
      originalArr[i] = cellMoney;
    }
    
    //System.out.println(Arrays.toString(originalArr));
    //System.out.println(Arrays.toString(originalArr));
    
    for (int i = 0; i < originalArr.length; i++) 
    {
        Money m1 = new Money();
        Money m2 = new Money();
        // Take dollar and cents of originalArr[i] and convert to String to double
        String str1 = Integer.toString(originalArr[i].myDollars) + "." + Integer.toString(originalArr[i].myCents);
        //System.out.println("String 1: " +str1);
        //double d1 = Double.parseDouble(str1);
        //System.out.println("Double 1: " + d1);

        BigDecimal num1 = new BigDecimal(str1);
        //System.out.println("BigDecimal 1: " + num1);
        // Inner Loop - 
        for (int j = i + 1; j < originalArr.length; j++) { 
            String str2 = Integer.toString(originalArr[j].myDollars) + "." + Integer.toString(originalArr[j].myCents);
            //System.out.println("String 2: " + str2);
            // Take dollar and cents of originalArr[j]  
            //System.out.println("2nd Value String " + str2);
            
            //double d2 = Double.parseDouble(str2);
            //System.out.println("Double 2: " + d2);
            BigDecimal num2 = new BigDecimal(str2);
            //System.out.println("BigDecimal 1: " + num2);


            // compare and swap
            if (num1.compareTo(num2) < 0)
            {
                //System.out.println("Swapping Entries..");
                temp = num1;
                num1 = num2;
                num2 = temp;
                
                // Convert d1 and d2 into Money object using String.Split
                str1 = num1.toString();
                //System.out.println(str1);
                //System.out.println("double to string str1 " + str1);
                str2 = num2.toString();
                //System.out.println(str2);
                //System.out.println("double to string str2 " + str2);

                String[] array1 = str1.split("[.]");
                
                /*System.out.println("array1[0] " + array1[0]);
                System.out.println("array1[1] " + array1[1]);*/

                originalArr[i].myDollars = Integer.parseInt(array1[0]);
                originalArr[i].myCents = Integer.parseInt(array1[1]);

                String[] array2 = str2.split("[.]");
                originalArr[j].myDollars = Integer.parseInt(array2[0]);
                originalArr[j].myCents = Integer.parseInt(array2[1]);
                //System.out.println("");

            }
            //System.out.println("");
            //System.out.println("");
        }
    }
    System.out.println(Arrays.toString(originalArr));
  }
	//This method should fix the problem of myCents > 99 or myCents < 0
	//Think carefully about which methods should call normalize()
	//COMPLETING THIS METHOD IS THE MAIN PART OF THIS ASSIGNMENT
  private void normalize()
  {
    //System.out.println("INSIDE the Normalize method"); // this display is only for testing purposes

    //Converted everything to cents to make life easy
    int tCents = (myDollars * 100) + myCents;

    //Checks to see if the cents are less than 0, then switched dollars and cents to 0
    if(tCents <= 0){
      myDollars = 0;
      myCents = 0;
    }
    //if the total cents are less than 100 then it will switch myDollars to 0 and myCents to the amount of tCents
    else if(tCents < 100){
      myDollars = 0;
      myCents = tCents;
    }
    //If tCents are greater than 0, then it will A) Find the truncated amount and make that to dollars and B) it will take the modulous and put that into cents
    else if(tCents >= 100){
      myDollars = tCents/100;
      myCents = tCents%100;
    }
  }
  
	//This method (sometimes incorrectly) displays the values to the screen 
	// THIS METHOD REQUIRES ONLY A SMALL MODIFICATION TO WORK PROPERLY
	public void print()
	{
    /*if(myCents < 10)
    {
      System.out.print("$" + myDollars+ ".0" + myCents);
    }
    else{
      System.out.print("$" + myDollars+ "." + myCents);
    }*/
    System.out.println(toString());
	}
  
	// This method (sometimes incorrectly) converts the object to a String and returns it	
	// THIS METHOD REQUIRES ONLY A SMALL MODIFICATION TO WORK PROPERLY
	public String toString()
	{
    //System.out.println("Inside to string");
    if(myCents < 10)
    {
      //System.out.println("Value is less than 10");
      return "$" + myDollars + ".0" + myCents;
    }
    else
    {
		  return "$" + myDollars + "." + myCents;
    }
		
	}
}
