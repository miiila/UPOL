array = [1,9,6,7,5,6,13,641,3,552,34]

class Node():

    def __init__(self):
        self.left = None
        self.right = None
        self.value = None

def DFS (root):
    if root.left != None:
        DFS(root.left)
    if root.right != None:
        DFS(root.right)

    print(root.value)

def BFS(root):
    queue = []
    queue.append(root)
    while len(queue) > 0:
        node = queue[0]
        del(queue[0])
        print(node.value)
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)

# CREATE TREE
tree = []
for index, val in enumerate(array):
    n = Node()
    n.value = val
    tree.append(n)
    if index != 0:
        if index%2 == 0:
            tree[int((index-2)/2)].right = n
        else:
            tree[int((index-1)/2)].left = n

# DFS Traverse

DFS(tree[0])
print('################')
BFS(tree[0])
