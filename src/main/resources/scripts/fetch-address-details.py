# -*- coding: utf-8 -*-
import os
import requests
import sqlalchemy

__baseSearchParam = dict(code='100000_full')
__baseUrl = 'https://geo.datav.aliyun.com/areas_v3/bound/geojson'


def get_geojson(code, full=True):
    if len(code) < 6:
        code = code + "0" * (6 - len(code))
    if full:
        code = code + "_full"
    print(code)
    resp = requests.get(__baseUrl + "?code=" + code)
    if resp.status_code != 200:
        return None
    return resp.text


def fill_borders(engine, Address, repeats=100):
    with engine.connect() as conn:
        for x in range(repeats):
            s = Address.select().where(Address.c.borders == None) \
                .where(Address.c.grade <= 3).order_by(Address.c.updated).limit(100)
            updates = []
            result = conn.execute(s)
            for row in result:
                code = '%d' % row['id']
                if len(code) < 6:
                    code = code + "0" * (6 - len(code))
                print(">> ", row['name'], code)
                borders = get_geojson(code, full=False)
                u = sqlalchemy.update(Address)
                u = u.values(dict(
                    borders=borders,
                    adcode=code,
                    updated=sqlalchemy.func.now(),
                ))
                u = u.where(Address.c.id == row['id'])
                updates.append(u)
            for r in updates:
                conn.execute(r)

def update_central_point(engine, Address):
    with engine.connect() as conn:
        s = Address.select().where(Address.c.borders != None)

if __name__ == '__main__':
    db_host = os.getenv("DB_HOST", "localhost")
    db_name = os.getenv("DB_NAME", "quickdb")
    db_user = os.getenv("DB_USER", "postgres")
    db_password = os.getenv("DB_PASSWORD", "quickstart.extvos")
    db_url = 'postgresql+psycopg2://{db_user}:{db_password}@{db_host}/{db_name}'.format(
        db_host=db_host, db_name=db_name, db_user=db_user, db_password=db_password
    )
    engine = sqlalchemy.create_engine(db_url, echo=True)
    meta = sqlalchemy.MetaData()
    Address = sqlalchemy.Table("builtin_addresses", meta, autoload_with=engine)
    fill_borders(engine, Address)
