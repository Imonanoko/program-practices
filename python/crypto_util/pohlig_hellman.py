from sage.all import *

def discrete_log_brute(g, h, mod,c=1):
    for x in range(mod):
        if (c*pow(g, x, mod)) % mod == h:
            return x
    raise ValueError("log not found")
# solve x for g^x = h mod p
# 模擬手算的版本
def pohlig_hellman(g:int,h:int,p:int):
    phi = p-1
    factors = list(factor(phi))
    # print(len(factors))
    p_list = []
    x_list = []
    for f in range(len(factors)):
        prime,exp = factors[f]
        p_list.append(pow(prime,exp))
        # a 是兩邊同開的次方
        a = phi//prime
        ai = []
        for r in range(1,exp+1):
            b = 1 
            for k in range(r-1):
                b = (b*pow(g,pow(prime,k)*ai[k]*a,p)) % p
            ai.append(discrete_log_brute(pow(g,pow(prime,r-1)*a,p),pow(h,a,p),p,b))
            a = a//prime
        
        x_list.append(0)
        for i in range(len(ai)):
            x_list[f] += (ai[i]*pow(prime,i)) % p_list[f]
    return crt(x_list,p_list)
print(pohlig_hellman(6,7,41))
print(pohlig_hellman(6,12,41))