array = [7,1,2,8,4,5,3,0]

def InsertionSort(array,len):

    for i in range(1, len): # for i = 1, i < (!) len, i++
        x = array[i]
        j = i-1
        while j>= 0 and array[j] > x:
            array[j+1] = array[j]
            j -= 1
        array[j+1] = x

InsertionSort(array, 8)

print(array)
