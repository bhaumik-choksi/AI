tree = {'arad':["timisoara","sibiu","zerind"],'timisoara':["lugoj"],'sibiu':["remnica","fagaras","oradea"],'zerind':[],'lugoj':["mehadia"],'remnica':["craiova","petesti"],'fagaras':["bucharest"],'oradea':[],'mehadia':[],'craiova':[],'petesti':[],'bucharest':[]}

queue = []

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

source = input('Enter source:\n')

destination = input('Enter destination\n')

queue.append(source)

while len(queue)!=0:
	parent = queue.pop(0)

	if parent == destination:
		find_path(parent)
		print(parent)
		exit()

	else:
		for c in tree[parent]:
			queue.append(c)
