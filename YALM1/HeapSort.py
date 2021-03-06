array = [7, 2, 8, 1, 5, 6, 3, 9, 4]

def Heapsort(array, len):
    BuildMaxHeap(array, len - 1)

    k = len - 1

    while k > 0:
        array[0], array[k] = array[k], array[0]
        k -= 1
        MaxHeapify(array, 0, k)

def BuildMaxHeap(array, k):
    for i in range(int(k/2), -1, -1):
        MaxHeapify(array, i, k)

def MaxHeapify(array, i, k):
    left = 2*i+1
    right = 2*i+2
    if left <= k and array[left] > array[i]:
        largest = left
    else:
        largest = i
    if right <= k and array[right] > array[largest]:
        largest = right
    if largest != i:
        array[i], array[largest] = array[largest], array[i]
        MaxHeapify(array, largest, k)

Heapsort(array, 9)

print(array)
