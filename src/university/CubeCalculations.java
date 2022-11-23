package university;

import Prog1Tools.IOTools;

public class CubeCalculations
{
    public static void main (String [] args)
    {
    	System.out.println("Welcome to the cube calculaton program. Please input your desired cube specs!\n");

		int len = IOTools.readInt("What's the side length of your cube in \"cm\"?: ");
		int[] materials = {5,15,25,10000}; // in kg/m^3
		int mat = IOTools.readInt("What's the material of cube? (Press 1,2,3): \n 1. plastic 2. wood 3. iron \n (all other inputs will be count as \"something else\").\n");
		switch (mat) {
			case (1) -> mat = materials[0];
			case (2) -> mat = materials[1];
			case (3) -> mat = materials[2];
			default -> {
				System.out.println("You have chosen \"something else\".");
				mat = materials[3];
			}
		}

		double vol = (double) len*len*len /(100*100*100); // in m^3
		double mass = vol*mat; // in kg

		final double g = 9.81; // in m/s^2
		final int stictionFactor = 1; // dimensionless 
		double force = mass*g*stictionFactor; // in N

    	System.out.println();
    	System.out.println("The side length of your cube will be: "+ len +" cm.");
    	System.out.println("The volume of your cube will be: "+ String.format("%.3f",vol) +" cubic meter.");
    	System.out.println("You hav chosen "+ mat +" as your material.");
    	System.out.println("The weight of your cube will be: "+ String.format("%.3f",mass) +" kg.");
    	System.out.println("The force necessary to get your cube moving on the ground will be: "+ String.format("%.3f",force) +" N.");
    	
    }
}