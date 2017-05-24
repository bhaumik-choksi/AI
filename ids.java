import java.util.*;
class ids
{ 
public static HashMap<String,String[]>cityMap = new HashMap<String,String[]>();

public static HashMap<String,Boolean>visited = new HashMap<String,Boolean>();

public static boolean found = false;

public static Stack<String>path = new Stack<String>(); //Stack for path

public static String dest = "Bucharest";

	public static void fillCityMap()
	{
		cityMap.put("Arad",new String[]{"Timisoara", "Sibiu", "Zerind"});
		cityMap.put("Timisoara",new String[]{"Lugoj"});
		cityMap.put("Lugoj",new String[]{"Mehadia"});
		cityMap.put("Sibiu",new String[]{"Rimnicu Vilcea", "Fagarus","Oradea"});
		cityMap.put("Rimnicu Vilcea",new String[]{"Craiova", "Pitesti"});
		cityMap.put("Fagarus",new String[]{"Bucharest"});
	}
	
	public static void main(String args[])
	{
		fillCityMap();
		String root = "Arad";
		String current;
		
		//IDS BEGIN
		for(int i=0;found==false;i++){
		System.out.println("Searching at max depth = "+i);
			while(!path.empty())
			{
				path.pop();
			}//empty the stack for the next round
		path.push(root);	
		ids(root,i);
		if(found)
		{	
			System.out.println("\nDestination: "+dest+" Found!");
			System.out.println("\n Path is "+path);
		}
		else
			System.out.println("\n Not found at depth "+i+"\n");	
		}
		//IDS END
	}
	public static void ids(String root,int depth)
	{
		if(found)
		return;
		
		if(depth==-1)
		return;	
		
		String children[];
		System.out.print("-> "+root);
		if(root.equals(dest))
		{
			path.push(dest);
			found = true;
			return;
		}
		else if(cityMap.containsKey(root)&&!found)
		{
			children = cityMap.get(root);
			for(int i=0;i<children.length;i++)
			{
				path.push(children[i]);
				ids(children[i],depth-1);
				
			}	
		}
		else
		{
			path.pop();
			return;
		}
		path.pop();
	}
}