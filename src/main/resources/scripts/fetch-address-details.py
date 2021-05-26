# -*- coding: utf-8 -*-
import requests

__baseSearchParam = dict(key='23b6143cf521f6efb6de4ef16d5e8e52', subdistrict=1, extensions='all')
__baseUrl = 'https://restapi.amap.com/v3/config/district'


def search_poi(code):
    params = dict(keywords=code.ljust(6, '0'))
    params.update(**__baseSearchParam)
    print('params:', params)
    resp = requests.get(__baseUrl, params)
    data = resp.json()
    if 'districts' in data and data['districts']:
        d = data['districts'][0]
        print('>', d['citycode'])
        print('>', d['adcode'])
        print('>', d['name'])
        print('>', d['center'])
        print('>', d['level'])
        print('>', d['polyline'])
        if d['districts']:
            for sd in d['districts']:
                print('  >', sd['citycode'])
                print('  >', sd['adcode'])
                print('  >', sd['name'])
                print('  >', sd['center'])
                print('  >', sd['level'])


if __name__ == '__main__':
    search_poi('440282')
