import java.util.*;
class star{
public static HashMap<String,Integer>heuristic = new HashMap<String,Integer>();
public static HashMap<String,String[]>adjacentMap = new HashMap<String,String[]>();
public static PriorityQueue<city> pq = new PriorityQueue<city>();
public static HashSet<String>visited = new HashSet<String>();
public static HashMap<String,Integer>map = new HashMap<String,Integer>();
public static HashMap<String,city>nodeMap = new HashMap<String,city>();

public static void fillNodeMap()
{
	nodeMap.put("Arad",new city("Arad",0,Integer.MAX_VALUE));
	nodeMap.put("Zerind",new city("Zerind",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Oradea",new city("Oradea",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Sibiu",new city("Sibiu",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Timisoara",new city("Timisoara",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Lugoj",new city("Lugoj",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Mehadia",new city("Mehadia",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Dobreta",new city("Dobreta",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Craiova",new city("Craiova",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Rimnicu",new city("Rimnicu",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Fagarus",new city("Fagarus",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Pitesti",new city("Pitesti",Integer.MAX_VALUE,Integer.MAX_VALUE));
	nodeMap.put("Bucharest",new city("Bucharest",Integer.MAX_VALUE,Integer.MAX_VALUE));
}
public static void fillMap()
{
		map.put("Arad-Timisoara",118);
		map.put("Timisoara-Lugoj",111);
		map.put("Arad-Zerind",75);
		map.put("Zerind-Oradea",71);
		map.put("Oradea-Sibiu",151);
		map.put("Arad-Sibiu",140);
		map.put("Sibiu-Rimnicu",80);
		map.put("Lugoj-Mehadia",70);
		map.put("Mehadia-Dobreta",75);
		map.put("Dobreta-Craiova",120);
		map.put("Craiova-Pitesti",138);
		map.put("Pitesti-Bucharest",101);
		map.put("Fagarus-Bucharest",211);
		map.put("Rimnicu-Pitesti",97);
		map.put("Rimnicu-Craiova",146);
		map.put("Sibiu-Fagarus",99);
} 
public static void fillCityMap()
	{
		adjacentMap.put("Arad",new String[]{"Timisoara", "Sibiu", "Zerind"});
		adjacentMap.put("Timisoara",new String[]{"Lugoj","Arad"});
		adjacentMap.put("Lugoj",new String[]{"Mehadia","Timisoara"});
		adjacentMap.put("Sibiu",new String[]{"Rimnicu", "Fagarus","Oradea","Arad"});
		adjacentMap.put("Rimnicu",new String[]{"Craiova", "Pitesti","Sibiu"});
		adjacentMap.put("Fagarus",new String[]{"Bucharest","Sibiu"});
		adjacentMap.put("Mehadia",new String[]{"Lugoj","Dobreta"});
		adjacentMap.put("Dobreta",new String[]{"Craiova","Mehadia"});
		adjacentMap.put("Craiova",new String[]{"Dobreta","Rimnicu","Pitesti"});
		adjacentMap.put("Oradea",new String[]{"Sibiu","Zerind"});
		adjacentMap.put("Pitesti",new String[]{"Bucharest","Rimnicu","Craiova"});
		adjacentMap.put("Zerind",new String[]{"Oradea","Arad"});
		adjacentMap.put("Bucharest",new String[]{"Pitesti","Fagarus"});
	}
public static void fillHeuristic()
{
	heuristic.put("Arad",366);
	heuristic.put("Bucharest",0);
	heuristic.put("Craiova",160);
	heuristic.put("Dobreta",242);
	heuristic.put("Eforie",161);
	heuristic.put("Fagarus",176);
	heuristic.put("Giurgiu",77);
	heuristic.put("Hirsova",151);
	heuristic.put("Iasi",226);
	heuristic.put("Lugoj",244);
	heuristic.put("Mehadia",241);
	heuristic.put("Neamt",234);
	heuristic.put("Oradea",380);
	heuristic.put("Pitesti",10);
	heuristic.put("Rimnicu",193);
	heuristic.put("Sibiu",253);
	heuristic.put("Timisoara",329);
	heuristic.put("Urziceni",80);
	heuristic.put("Valsui",199);
	heuristic.put("Zerind",374);
} 
public static int getDist(String s)
{
	int d=-1;
	
	if(heuristic.containsKey(s))
	d = heuristic.get(s);
	
	return d;
} 
public static int getAtoB(String a, String b)
{
	int ans = Integer.MAX_VALUE;
	String temp = a+"-"+b;
	if(map.containsKey(temp))
		ans = map.get(temp);
	
	temp = b+"-"+a;
	if(map.containsKey(temp))
		ans = map.get(temp);
	
	return ans;	
}

public static void aStar(String s)
{
	String[] children;
	String currCity = s;
	visited.add(currCity);
	
	//Scanner sc = new Scanner(System.in);
	
	while(true)
	{
		
		visited.add(currCity);
		//int lmn = sc.nextInt();
		//if(lmn==1)
		//System.exit(0);
		
		System.out.println("Currently at: "+currCity);
		System.out.println("Cost from source for current city is: "+nodeMap.get(currCity).costFromSource);
		System.out.println("Total Cost for current city is: "+nodeMap.get(currCity).total);
		System.out.println();

			children = adjacentMap.get(currCity);
			for(String ch:children)
			{
					city m = nodeMap.get(ch);
					m.costFromSource = Math.min(m.costFromSource,getAtoB(currCity,ch)+nodeMap.get(currCity).costFromSource);
					m.total = m.costFromSource + getDist(ch);
					nodeMap.put(ch,m);
					if(visited.contains(ch)==false)
					pq.add(nodeMap.get(ch));
					
			}
		
		while(visited.contains(currCity)!=false)
		currCity = pq.remove().name;
		
		if(currCity.equals("Bucharest"))
		{
			System.out.println("Reached Bucharest with cost: "+nodeMap.get("Bucharest").total);
			return;
		}
		
	}
}
public static void main(String args[])
{
	fillHeuristic();
	fillMap();
	fillCityMap();
	fillNodeMap();
	
	String source = "Arad";
	aStar(source);			
}
}
class city implements Comparable<city>
{ 
public String name;
public int costFromSource;
public int total;

	public city(String n,int costFromSource,int tot)
	{
		name=n;
		this.costFromSource=costFromSource;
		total=tot;
	}
	public int compareTo(city n) {  
	    if(total>n.total){  
	        return 1;  
	    }else if(total<n.total){  
	        return -1;  
	    }else{
	    return 0;  
	    }  
 	}  
}