import java.util.*;
class aidfs
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
		
		//DFS BEGIN
		path.push(root);
		dfs(root);
		if(found)
			System.out.println("\nDestination: "+dest+" Found!");
		//DFS END
		System.out.println("Path is "+path);
	}
	public static void dfs(String root)
	{
		if(found)
		return;
		String children[];
		System.out.println("Currently at city "+root);
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
				dfs(children[i]);
				
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