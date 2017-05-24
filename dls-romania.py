tree = {'arad':["timisoara","sibiu","zerind"],'timisoara':["lugoj"],'sibiu':["remnica","fagaras","oradea"],'zerind':[],'lugoj':["mehadia"],'remnica':["craiova","petesti"],'fagaras':["bucharest"],'oradea':[],'mehadia':[],'craiova':[],'petesti':[],'bucharest':[]}

stack = []


def find_path(s):
	if s == 'None':
		return
	else:
		for k,v in tree.items():
			for c in v:
				if c == s:
					find_path(k)
					print(k)
					return

def DLS(src,target,maxDepth):
 
    if src == target : return True

    # If reached the maximum depth, stop recursing.
    if maxDepth <= 0 : return False

    # Recur for all the vertices adjacent to this vertex
    for i in tree[src]:
            if(DLS(i,target,maxDepth-1)):
                return True
    return False


source = input('Enter source:\n')

destination = input('Enter destination\n')

limit = int(input('Enter limit\n'))

if(DLS(source,destination,limit)):
	find_path(destination)
else:
	print('Limit Reached')