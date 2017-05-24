/*
 * Implementation of graph coloring using genetic algorithms
 * Takes adjacency graph and no. of colors as the input
 * The output is the optimal coloring combination(s)
 * The fitness function checks if the adjacent nodes have the same color or not
 * If they do have the same color, the sample is not fit
 *
 * Bhaumik Choksi
*/

import java.util.*;
class gcolor{
	
public static int[][] graph;	
public static Vector<int[]> performCross(Vector<int[]>population,int pop_size, int n, int nc)
{
	Vector<int[]>ans = new Vector<int[]>();
	for(int i=0;i<population.size()-1;i=i+2)
	{
		int[] parent1 = population.elementAt(i);
		int[] parent2 = population.elementAt(i+1);
		
		int[] child1 = new int[n];
		int[] child2 = new int[n];
		
		for(int j=0;j<n;j++)
		{
			if(j<n/2)
			{
				child1[j] = parent1[j];
				child2[j] = parent2[j];			}
			else
			{
				child1[j] = parent2[j];
				child2[j] = parent1[j];
			}
		}
		
		ans.addElement(child1);
		ans.addElement(child2);
	}	
	return ans;		
}	
public static Vector<int[]> getRandomPopulation(int n,int size,int nc)
{
	Vector<int[]>v = new Vector<int[]>();
	Random r = new Random();
	for(int i=0;i<size;i++)
	{
		int a[] = new int[n]; //Array indices denote nodes in the graph
		for(int j=0;j<n;j++)
		{
			a[j] = r.nextInt(nc); //Element at any given index represents color of that node
								  //Colors are represented as numbers
		}	
		v.addElement(a);		
	}
	return v;
}
public static Vector<int[]> applyFitnessFunction(Vector<int[]>population,int n,int nc)
{
	Vector<int[]>fitPopulation = new Vector<int[]>();
	for(int[] sample:population)
	{
		boolean fit = true;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(graph[i][j]==1 && sample[i]==sample[j] && i!=j)
				{
					fit = false;
					break;
				}
			}
		}
		if(fit)
			fitPopulation.addElement(sample);
	}
	return fitPopulation;
}
public static Vector<int[]> applyMutation(Vector<int[]>population,int n,int nc)
{
	Random r = new Random();
	Vector<int[]>answer = new Vector<int[]>();
	
	for(int[] sample:population)
	{
		int willChange = r.nextInt(2); //50% change of mutation.
		if(willChange==1) //If willChange is 1, mutate. If 0, don't.
		{
			int position = r.nextInt(n); //Position of mutation is also random
			int newColor = r.nextInt(nc);	//New color to be assigned is also random
			sample[position] = newColor;
		}
		answer.addElement(sample);
	}	
		
	return answer;		
}	
public static void main(String args[])
{
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter number of nodes");
	int n = sc.nextInt();
	graph = new int[n][n];
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			if(i!=j)
			{	
			System.out.println("Is "+i+" ,"+j+" connected? Yes = 1 No = 0");
			graph[i][j] = sc.nextInt();
			}
			else
				graph[i][j] = 1; //Nodes are connected to themselves
				
		}
	}
	System.out.println("Enter size of random population");
	int pop_size = sc.nextInt();
	System.out.println("Enter no. of colors to be used");
	int nc = sc.nextInt();
	Vector<int[]>rand_pop = getRandomPopulation(n,pop_size,nc);

	rand_pop = performCross(rand_pop,pop_size,n,nc);

	rand_pop = applyMutation(rand_pop,n,nc);
	
	rand_pop = applyFitnessFunction(rand_pop,n,nc);	
		
	if(rand_pop.size()==0)
		System.out.println("No fit sample found");
	else{
		System.out.println("Fit samples are");
		for(int[] x: rand_pop)
		System.out.println(Arrays.toString(x));
	}		
}
}