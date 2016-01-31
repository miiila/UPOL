array = [7,1,2,8,4,5,3,0]

def SelectionSort(array, len):

    for i in range(0, len-1):
        m = i #Remembered position of minimal element
        for j in range(i+1, len):
            if array[j] < array[m]:
                m = j
        array[i], array[m] = array[m], array[i]

SelectionSort(array, 8)

print(array)
