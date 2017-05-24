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


source = input('Enter source:\n')

destination = input('Enter destination\n')

stack.append(source)

while len(stack)!=0:
	parent = stack.pop(-1)
	print(parent)

	if parent == destination:
		print('\nPath is:\n')
		find_path(destination)
		print(parent)
		exit()
	else:
		for c in reversed(tree[parent]):
			stack.append(c)