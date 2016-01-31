array = [7,1,2,8,4,5,3,0]

def BubbleSort(array, len):
    for i in range(len-1,0,-1):
        for j in range(0,i):
            if array[j] > array[j+1]:
                array[j],array[j+1] = array[j+1],array[j]

BubbleSort(array, 8)

print(array)
