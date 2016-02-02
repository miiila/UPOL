n = 10
i = 0
j = 0
m = 0

def Insert(F, x):
    global n,i,j,m

    if m == n:
        return False

    F[j] = x
    j = (j+1) % n
    m = m+1

    return True

def Delete(F, x):
    global n,i,j,m,y

    if m == 0:
        return false

    y = F[i]
    i = (i+1) % n
    m = m-1

    return True

F = {}

Insert(F, 5)
Insert(F, 7)
Insert(F, 1)

print(F)

y = None
Delete(F,y)
print(y)
print(y)

print(F)
