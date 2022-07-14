import sys

for line in sys.stdin:
    l = line.strip().split(',')
    code = l[0].rstrip('0')
    if len(code)<6 and len(code)%2!=0:
        code=code+'0'
    elif len(code)>6 and len(code)<9:
        code=code+'0'*(9-len(code))
    name = l[1].strip()
    print(code+","+name)

