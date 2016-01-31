# Pivot on the end

array = [7,1,9,5,4,8,3,2,0]

def Quicksort(array, left, right):
    x = array[int((left+right)/2)]
    i = left
    j = right

    while i <= j:
        while array[i] < x:
            i += 1
        while x < array[j]:
            j -= 1
        if i > j:
            break
        array[i], array[j] = array[j], array[i]
        i += 1
        j -= 1
    if left < j:
        Quicksort(array, left, j)
    if i < right:
        Quicksort(array, i, right)


Quicksort(array, 0, 8)

print(array)
