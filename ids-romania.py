tree = {'arad':["timisoara","sibiu","zerind"],'timisoara':["lugoj"],'sibiu':["remnica","fagaras","oradea"],'zerind':[],'lugoj':["mehadia"],'remnica':["craiova","petesti"],'fagaras':["bucharest"],'oradea':[],'mehadia':[],'craiova':[],'petesti':[],'bucharest':[]}

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
	if src == target: return True
	if maxDepth <= 0: return False

	for i in tree[src]:
		if DLS(i,target,maxDepth-1):
			return True

	return False

def ids(limit):

	print('Limit: {}'.format(limit))

	for i in range(1,limit):
		if DLS(source,destination,i):
			find_path(destination)



source = input('Enter source:\n')

destination = input('Enter destination\n')

limit = int(input('Enter limit\n'))

ids(limit)