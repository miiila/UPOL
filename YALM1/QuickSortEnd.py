# Pivot on the end

array = [7,1,9,5,4,8,3,2,0]

def Partition(array, left, right):
    x = array[right]
    i = left - 1
    j = right

    while True:
        i += 1 # Initial increment before while
        while array[i] < x:
            i += 1
        j -= 1 # Initial decrement before while
        while x < array[j]:
            j -= 1
            if i >= j:
                break
        if i >= j:
            break
        array[i], array[j] = array[j], array[i]
    array[i], array[right] = array[right], array[i]
    return i

def Quicksort(array, left, right):
    if left < right:
        i = Partition(array, left, right)
        Quicksort(array, left, i - 1)
        Quicksort(array, i + 1, right)

Quicksort(array, 0, 8)

print(array)
