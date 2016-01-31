array = [14, 7, 13, 5, 15, 4, 8, 3, 2, 10, 1, 11, 9, 12, 6]

def ShellSort(array, len):
    h = 1
    while 3*h+1 < len/2: # Identify initial sequence step
        h = 3*h+1

    while h>0:
        for k in range(0, h-1):
            i = k+h # Identify members of sequence
            while i < len:
                x = array[i]
                j = i - h
                while j>= 0 and array[j] > x: # Insertion sort starts  here
                    array[j+h] = array[j]
                    j = j - h
                array[j+h] = x
                i = i + h
        h = int(h/3)

ShellSort(array, 15)

print(array)
