def calc(expression):
    global exp 
    exp = expression
    exp = exp.replace(" ","")
    if find(0,"(") != -1:
        evaluateParenthesis()
    return evaluate()
    
def evaluate():
    nums = []
    ops = []
    opsi = []
    number = False
    for i in range(0,len(exp)):
        if exp[i] == '0' or exp[i] == '1' or exp[i] == '2'or exp[i] == '3'or exp[i] == '4'or exp[i] == '5'or exp[i] == '6'or exp[i] == '7'or exp[i] == '8'or exp[i] == '9':
            number = True
        elif exp[i] == '+' or exp[i] == '-' or exp[i] == '*' or exp[i] == '/':
            if number:
                ops.append(exp[i])
                opsi.append(i)
            number = False

    if len(ops) == 0:
        return float(exp)
    
    nums.append(float(exp[0:opsi[0]]))
    
    for i in range(1,len(opsi)):
        nums.append(float(exp[opsi[i-1]+1:opsi[i]]))
    
    nums.append(float(exp[opsi[len(opsi)-1]+1:len(exp)]))
    
    j = 0
    while j != len(ops):
        if ops[j] == '*':
            nums[j] = nums[j] * nums[j+1]
            del(nums[j+1])
            del(ops[j])
            j-=1
        elif ops[j] == '/':
            nums[j] = nums[j] / nums[j+1]
            del(nums[j+1])
            del(ops[j])
            j-=1
        j+=1

    j = 0
    while j != len(ops):
        if ops[j] == '+':
            nums[j] = nums[j] + nums[j+1]
            del(nums[j+1])
            del(ops[j])
            j-=1
        elif ops[j] == '-':
            nums[j] = nums[j] - nums[j+1]
            del(nums[j+1])
            del(ops[j])
            j-=1
        j+=1
    return nums[0]


def evaluateParenthesis():
    global exp
    pib = 0
    pie = len(exp)
    pf = False
    lfp = True
    neg = False
    
    while find(0,'(') != -1:
        if lfp:
            iii = 0
            while iii < len(exp):
                if exp[iii] == '(':
                    pib = iii
                iii+=1
        ii = pib+1
        while ii<len(exp):
            if exp[ii] == ')':
                pie = ii
                pf = True
                lfp = True
                ii = len(exp)
            elif exp[ii] == '(':
                pib = ii
                pf = False
                lfp = False
                ii = len(exp)
            ii+=1 
        if pf:
            if pib == 0:
                neg = False
            elif pib ==1:
                if exp[0] == '-':
                    neg = True
                else:
                    neg = False
            else:
                if (exp[pib-1] == '-') and ( exp[pib-2] == '+' or exp[pib-2] == '-' or exp[pib-2] == '*' or exp[pib-2] == '/' or exp[pib-2] == '('):
                    neg = True
                else:
                    neg = False
        if neg:
            exp = exp[0:pib-1]+str(evaluateP(pib,pie,neg))+exp[pib-1:len(exp)]
        else:
            exp = exp[0:pib]+str(evaluateP(pib,pie,neg))+exp[pib:len(exp)]

def evaluateP(i1, i2, neg):
    global exp
    nums = []
    ops = []
    opsi = []
    number = False
    for i in range(i1+1,i2):
        if exp[i] == '0' or exp[i] == '1' or exp[i] == '2'or exp[i] == '3'or exp[i] == '4'or exp[i] == '5'or exp[i] == '6'or exp[i] == '7'or exp[i] == '8'or exp[i] == '9':
            number = True
        elif exp[i] == '+' or exp[i] == '-' or exp[i] == '*' or exp[i] == '/':
            if number:
                ops.append(exp[i])
                opsi.append(i)
            number = False

    if len(ops) == 0:
        if neg:    
            value= float(exp[i1+1:i2])*-1
        else:
            value =  float(exp[i1+1:i2])
        if neg:
            exp = delete(i1-1,i2+1)
        else:
            exp = delete(i1,i2+1)
        return value
    
    nums.append(float(exp[i1+1:opsi[0]]))
    for i in range(1,len(opsi)):
        nums.append(float(exp[opsi[i-1]+1:opsi[i]]))
    nums.append(float(exp[opsi[len(opsi)-1]+1:i2]))
    

    
    
    if neg:
        exp = delete(i1-1,i2+1)
    else:
        exp = delete(i1,i2+1)
        
    i = 0
    while i!=len(ops):
        if ops[i] == '*':
            nums[i] = nums[i] * nums[i+1]
            del(nums[i+1])
            del(ops[i])
            i-=1
        elif ops[i] == '/':
            nums[i] = nums[i] / nums[i+1]
            del(nums[i+1])
            del(ops[i])
            i-=1                
        i+=1
    i = 0
    while i!=len(ops):
        if ops[i] == '+':
            nums[i] = nums[i] + nums[i+1]
            del(nums[i+1])
            del(ops[i])
            i-=1
        elif ops[i] == '-':
            nums[i] = nums[i] - nums[i+1]
            del(nums[i+1])
            del(ops[i])
            i-=1                
        i+=1
    if neg:
        return nums[0] *-1
    return nums[0]
    
def find(index,c):
    for i in range(index, len(exp)):
        if exp[i] == c:
            return i
    return -1
        
def delete(i1, i2):
    return exp[0:i1] +exp[i2:len(exp)]
