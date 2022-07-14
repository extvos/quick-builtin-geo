import sys

SEGS = (2, 2, 2, 3)


def get_path(code):
    p = []
    n = 0
    for x in SEGS:
        if len(code) <= n + x:
            break
        p.append(code[0:n+x])
        n = n + x
    return '/'.join(p)


for line in sys.stdin:
    l = line.strip().split(',')
    code = l[0].rstrip('0')
    grade = len(code) / 2
    parent_id = '0'
    parent_path = ''
    if len(code) <= 6:
        if len(code) % 2 != 0:
            code = code + '0'
        grade = len(code) / 2
        parent_id = code[:len(code) - 2] if len(code) > 2 else '0'
    elif 6 < len(code) <= 9:
        grade = 4
        code = code + '0' * (9 - len(code))
        parent_id = code[:6]
    elif len(code) > 9:
        grade = 5
        parent_id = code[:9]
        if len(code) < 12:
            code = code + '0' * (12 - len(code))
    adcode = code + '0' * (6 - len(code)) if len(code) < 6 else code[:6]
    name = l[1].strip()
    grade = '%d' % grade
    parent_path = get_path(code)
    s = "INSERT INTO builtin_addresses(id, name, grade, adcode, parent_id, parent_path) VALUES(" \
        + code + ",'" \
        + name + "'," \
        + grade + ",'" \
        + adcode + "'," \
        + parent_id + ",'" \
        + parent_path + "'" \
        + ") ON CONFLICT (id) DO UPDATE SET name='" + name \
        + "', grade = " + grade \
        + ", adcode = '" + adcode \
        + "', parent_id = " + parent_id \
        + ", parent_path = '" + parent_path \
        + "', updated=NOW();"
    print(s)
