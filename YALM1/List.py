array = [1,9,6,7,5,6]

class List:

    def __init__(self):
        self.head = None

class Node:

    def __init__(self):
        self.value = None
        self.next = None

def ListInsert(list, value):
    node = Node()
    node.value = value
    node.next = list.head
    list.head = node

def ListPrint(list):
    node = list.head
    while node != None:
        print(node.value)
        node = node.next

def ListSearch(list, needle):
    node = list.head
    while node != None and node.value != needle:
        node = node.next

    return node

def ListDelete(list, value):
    if list.head == None:
        return False
    if list.head.value == value:
        list.head = list.head.next
    x = list.head
    while True:
        y = x.next
        if y == None:
            return False
        if y.value == value:
            x.next = y.next
            return True
        x = x.next

################################
list = List()

ListPrint(list)

for i in array:
    ListInsert(list,i)

ListPrint(list)

print(ListSearch(list, 23))

print(ListSearch(list, 7))

print(ListDelete(list, 9))

ListPrint(list)
