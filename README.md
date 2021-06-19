# quick-builtin-geo

内置地址信息服务，提供了一个分5个等级的中华人民共和国行政区域划分数据库。



## 数据表

### `Address` 中华人民共和国行政区域地址表

```sql
CREATE TABLE IF NOT EXISTS `builtin_addresses` (
	`id` BIGINT(20) NOT NULL COMMENT '主键ID，与国标匹配',
	`name` VARCHAR(32) NOT NULL COMMENT '名称' COLLATE 'utf8mb4_general_ci',
	`grade` INT(11) NULL DEFAULT 0 COMMENT '级别： 1=省，2=市，3=县，4=镇，5=乡' ,
	`city_code` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '城市区号' COLLATE 'utf8mb4_general_ci',
	`zip_code` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '邮政编码' COLLATE 'utf8mb4_general_ci',
	`lat` DOUBLE NULL DEFAULT 0.0 COMMENT '中心坐标latitude' ,
	`lng` DOUBLE NULL DEFAULT 0.0 COMMENT '中心坐标longitude' ,
	`parent_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '父节点ID' ,
	`parent_path` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '所有父节点路径' COLLATE 'utf8mb4_general_ci',
	`pinyin` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '拼音' COLLATE 'utf8mb4_general_ci',
	`pinyin_initial` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '拼音首字母' COLLATE 'utf8mb4_general_ci',
	`borders` TEXT NULL DEFAULT NULL COMMENT '边界坐标(lat,lng),以分号分割' COLLATE 'utf8mb4_general_ci',
	`comment` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注信息' COLLATE 'utf8mb4_general_ci',
	`updated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间' ,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `grade` (`grade`) USING BTREE,
	INDEX `parent_id` (`parent_id`) USING BTREE,
	INDEX `city_code` (`city_code`) USING BTREE,
	INDEX `name` (`name`) USING BTREE,
	INDEX `pinyin` (`pinyin`) USING BTREE,
	INDEX `pinyin_initial` (`pinyin_initial`) USING BTREE
)
COMMENT='中华人民共和国行政区域地址表'
COLLATE='utf8mb4_general_ci';
```



## 接口列表

### 省-直辖市-自治区 列表


**接口地址**:`/_builtin/address/provinces`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取列表，查询条件组织，请参考： <a href="https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md">https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md</a></p>



**请求参数**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型 | schema |
| -------- | -------- | -------- | -------- | -------- | ------ |
| queries  | queries  | query    | false    | object   |        |


**响应状态**:


| 状态码 | 说明         | schema                |
| ------ | ------------ | --------------------- |
| 200    | OK           | Result«List«Address»» |
| 401    | Unauthorized |                       |
| 403    | Forbidden    |                       |
| 404    | Not Found    |                       |


**响应参数**:


| 参数名称                  | 参数说明 | 类型           | schema         |
| ------------------------- | -------- | -------------- | -------------- |
| code                      |          | integer(int32) | integer(int32) |
| count                     |          | integer(int64) | integer(int64) |
| data                      |          | array          | Address        |
| &emsp;&emsp;borders       |          | string         |                |
| &emsp;&emsp;cityCode      |          | string         |                |
| &emsp;&emsp;grade         |          | integer(int32) |                |
| &emsp;&emsp;id            |          | integer(int64) |                |
| &emsp;&emsp;lat           |          | number(double) |                |
| &emsp;&emsp;lng           |          | number(double) |                |
| &emsp;&emsp;name          |          | string         |                |
| &emsp;&emsp;parentId      |          | integer(int64) |                |
| &emsp;&emsp;parentNames   |          | string         |                |
| &emsp;&emsp;parentPath    |          | string         |                |
| &emsp;&emsp;pinyin        |          | string         |                |
| &emsp;&emsp;pinyinInitial |          | string         |                |
| &emsp;&emsp;updated       |          | string         |                |
| &emsp;&emsp;zipCode       |          | string         |                |
| error                     |          | string         |                |
| msg                       |          | string         |                |
| page                      |          | integer(int64) | integer(int64) |
| pageSize                  |          | integer(int64) | integer(int64) |
| total                     |          | integer(int64) | integer(int64) |


**响应示例**:
```javascript
{
	"code": 0,
	"count": 0,
	"data": [
		{
			"borders": "",
			"cityCode": "",
			"grade": 0,
			"id": 0,
			"lat": 0,
			"lng": 0,
			"name": "",
			"parentId": 0,
			"parentNames": "",
			"parentPath": "",
			"pinyin": "",
			"pinyinInitial": "",
			"updated": "yyyy-MM-dd hh:mm:ss",
			"zipCode": ""
		}
	],
	"error": "",
	"msg": "",
	"page": 0,
	"pageSize": 0,
	"total": 0
}
```

### 市-城市 列表


**接口地址**:`/_builtin/address/province/{provinceId}/cities`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取 省/直辖市/自治区 下，查询条件组织，请参考： <a href="https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md">https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md</a> </p>



**请求参数**:


**请求参数**:


| 参数名称   | 参数说明   | 请求类型 | 是否必须 | 数据类型       | schema |
| ---------- | ---------- | -------- | -------- | -------------- | ------ |
| provinceId | provinceId | path     | true     | integer(int64) |        |
| queries    | queries    | query    | false    | object         |        |


**响应状态**:


| 状态码 | 说明         | schema                |
| ------ | ------------ | --------------------- |
| 200    | OK           | Result«List«Address»» |
| 401    | Unauthorized |                       |
| 403    | Forbidden    |                       |
| 404    | Not Found    |                       |


**响应参数**:


| 参数名称                  | 参数说明 | 类型           | schema         |
| ------------------------- | -------- | -------------- | -------------- |
| code                      |          | integer(int32) | integer(int32) |
| count                     |          | integer(int64) | integer(int64) |
| data                      |          | array          | Address        |
| &emsp;&emsp;borders       |          | string         |                |
| &emsp;&emsp;cityCode      |          | string         |                |
| &emsp;&emsp;grade         |          | integer(int32) |                |
| &emsp;&emsp;id            |          | integer(int64) |                |
| &emsp;&emsp;lat           |          | number(double) |                |
| &emsp;&emsp;lng           |          | number(double) |                |
| &emsp;&emsp;name          |          | string         |                |
| &emsp;&emsp;parentId      |          | integer(int64) |                |
| &emsp;&emsp;parentNames   |          | string         |                |
| &emsp;&emsp;parentPath    |          | string         |                |
| &emsp;&emsp;pinyin        |          | string         |                |
| &emsp;&emsp;pinyinInitial |          | string         |                |
| &emsp;&emsp;updated       |          | string         |                |
| &emsp;&emsp;zipCode       |          | string         |                |
| error                     |          | string         |                |
| msg                       |          | string         |                |
| page                      |          | integer(int64) | integer(int64) |
| pageSize                  |          | integer(int64) | integer(int64) |
| total                     |          | integer(int64) | integer(int64) |


**响应示例**:
```javascript
{
	"code": 0,
	"count": 0,
	"data": [
		{
			"borders": "",
			"cityCode": "",
			"grade": 0,
			"id": 0,
			"lat": 0,
			"lng": 0,
			"name": "",
			"parentId": 0,
			"parentNames": "",
			"parentPath": "",
			"pinyin": "",
			"pinyinInitial": "",
			"updated": "yyyy-MM-dd hh:mm:ss",
			"zipCode": ""
		}
	],
	"error": "",
	"msg": "",
	"page": 0,
	"pageSize": 0,
	"total": 0
}
```

### 县（区）列表


**接口地址**:`/_builtin/address/city/{cityId}/counties`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取某个市下面的县（区），查询条件组织，请参考： <a href="https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md">https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md</a></p>



**请求参数**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型       | schema |
| -------- | -------- | -------- | -------- | -------------- | ------ |
| cityId   | cityId   | path     | true     | integer(int64) |        |
| queries  | queries  | query    | false    | object         |        |


**响应状态**:


| 状态码 | 说明         | schema                |
| ------ | ------------ | --------------------- |
| 200    | OK           | Result«List«Address»» |
| 401    | Unauthorized |                       |
| 403    | Forbidden    |                       |
| 404    | Not Found    |                       |


**响应参数**:


| 参数名称                  | 参数说明 | 类型           | schema         |
| ------------------------- | -------- | -------------- | -------------- |
| code                      |          | integer(int32) | integer(int32) |
| count                     |          | integer(int64) | integer(int64) |
| data                      |          | array          | Address        |
| &emsp;&emsp;borders       |          | string         |                |
| &emsp;&emsp;cityCode      |          | string         |                |
| &emsp;&emsp;grade         |          | integer(int32) |                |
| &emsp;&emsp;id            |          | integer(int64) |                |
| &emsp;&emsp;lat           |          | number(double) |                |
| &emsp;&emsp;lng           |          | number(double) |                |
| &emsp;&emsp;name          |          | string         |                |
| &emsp;&emsp;parentId      |          | integer(int64) |                |
| &emsp;&emsp;parentNames   |          | string         |                |
| &emsp;&emsp;parentPath    |          | string         |                |
| &emsp;&emsp;pinyin        |          | string         |                |
| &emsp;&emsp;pinyinInitial |          | string         |                |
| &emsp;&emsp;updated       |          | string         |                |
| &emsp;&emsp;zipCode       |          | string         |                |
| error                     |          | string         |                |
| msg                       |          | string         |                |
| page                      |          | integer(int64) | integer(int64) |
| pageSize                  |          | integer(int64) | integer(int64) |
| total                     |          | integer(int64) | integer(int64) |


**响应示例**:
```javascript
{
	"code": 0,
	"count": 0,
	"data": [
		{
			"borders": "",
			"cityCode": "",
			"grade": 0,
			"id": 0,
			"lat": 0,
			"lng": 0,
			"name": "",
			"parentId": 0,
			"parentNames": "",
			"parentPath": "",
			"pinyin": "",
			"pinyinInitial": "",
			"updated": "yyyy-MM-dd hh:mm:ss",
			"zipCode": ""
		}
	],
	"error": "",
	"msg": "",
	"page": 0,
	"pageSize": 0,
	"total": 0
}
```

### 镇（街道）列表


**接口地址**:`/_builtin/address/county/{countyId}/towns`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取某个县（区）下属的镇（街道），查询条件组织，请参考： <a href="https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md">https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md</a></p>



**请求参数**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型       | schema |
| -------- | -------- | -------- | -------- | -------------- | ------ |
| countyId | countyId | path     | true     | integer(int64) |        |
| queries  | queries  | query    | false    | object         |        |


**响应状态**:


| 状态码 | 说明         | schema                |
| ------ | ------------ | --------------------- |
| 200    | OK           | Result«List«Address»» |
| 401    | Unauthorized |                       |
| 403    | Forbidden    |                       |
| 404    | Not Found    |                       |


**响应参数**:


| 参数名称                  | 参数说明 | 类型           | schema         |
| ------------------------- | -------- | -------------- | -------------- |
| code                      |          | integer(int32) | integer(int32) |
| count                     |          | integer(int64) | integer(int64) |
| data                      |          | array          | Address        |
| &emsp;&emsp;borders       |          | string         |                |
| &emsp;&emsp;cityCode      |          | string         |                |
| &emsp;&emsp;grade         |          | integer(int32) |                |
| &emsp;&emsp;id            |          | integer(int64) |                |
| &emsp;&emsp;lat           |          | number(double) |                |
| &emsp;&emsp;lng           |          | number(double) |                |
| &emsp;&emsp;name          |          | string         |                |
| &emsp;&emsp;parentId      |          | integer(int64) |                |
| &emsp;&emsp;parentNames   |          | string         |                |
| &emsp;&emsp;parentPath    |          | string         |                |
| &emsp;&emsp;pinyin        |          | string         |                |
| &emsp;&emsp;pinyinInitial |          | string         |                |
| &emsp;&emsp;updated       |          | string         |                |
| &emsp;&emsp;zipCode       |          | string         |                |
| error                     |          | string         |                |
| msg                       |          | string         |                |
| page                      |          | integer(int64) | integer(int64) |
| pageSize                  |          | integer(int64) | integer(int64) |
| total                     |          | integer(int64) | integer(int64) |


**响应示例**:
```javascript
{
	"code": 0,
	"count": 0,
	"data": [
		{
			"borders": "",
			"cityCode": "",
			"grade": 0,
			"id": 0,
			"lat": 0,
			"lng": 0,
			"name": "",
			"parentId": 0,
			"parentNames": "",
			"parentPath": "",
			"pinyin": "",
			"pinyinInitial": "",
			"updated": "yyyy-MM-dd hh:mm:ss",
			"zipCode": ""
		}
	],
	"error": "",
	"msg": "",
	"page": 0,
	"pageSize": 0,
	"total": 0
}
```

### 村（社区）列表


**接口地址**:`/_builtin/address/town/{townId}/villages`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取某个镇（街道）下属的村（社区）列表，查询条件组织，请参考： <a href="https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md">https://gitlab.inodes.cn/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md</a></p>



**请求参数**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型       | schema |
| -------- | -------- | -------- | -------- | -------------- | ------ |
| townId   | townId   | path     | true     | integer(int64) |        |
| queries  | queries  | query    | false    | object         |        |


**响应状态**:


| 状态码 | 说明         | schema                |
| ------ | ------------ | --------------------- |
| 200    | OK           | Result«List«Address»» |
| 401    | Unauthorized |                       |
| 403    | Forbidden    |                       |
| 404    | Not Found    |                       |


**响应参数**:


| 参数名称                  | 参数说明 | 类型           | schema         |
| ------------------------- | -------- | -------------- | -------------- |
| code                      |          | integer(int32) | integer(int32) |
| count                     |          | integer(int64) | integer(int64) |
| data                      |          | array          | Address        |
| &emsp;&emsp;borders       |          | string         |                |
| &emsp;&emsp;cityCode      |          | string         |                |
| &emsp;&emsp;grade         |          | integer(int32) |                |
| &emsp;&emsp;id            |          | integer(int64) |                |
| &emsp;&emsp;lat           |          | number(double) |                |
| &emsp;&emsp;lng           |          | number(double) |                |
| &emsp;&emsp;name          |          | string         |                |
| &emsp;&emsp;parentId      |          | integer(int64) |                |
| &emsp;&emsp;parentNames   |          | string         |                |
| &emsp;&emsp;parentPath    |          | string         |                |
| &emsp;&emsp;pinyin        |          | string         |                |
| &emsp;&emsp;pinyinInitial |          | string         |                |
| &emsp;&emsp;updated       |          | string         |                |
| &emsp;&emsp;zipCode       |          | string         |                |
| error                     |          | string         |                |
| msg                       |          | string         |                |
| page                      |          | integer(int64) | integer(int64) |
| pageSize                  |          | integer(int64) | integer(int64) |
| total                     |          | integer(int64) | integer(int64) |


**响应示例**:
```javascript
{
	"code": 0,
	"count": 0,
	"data": [
		{
			"borders": "",
			"cityCode": "",
			"grade": 0,
			"id": 0,
			"lat": 0,
			"lng": 0,
			"name": "",
			"parentId": 0,
			"parentNames": "",
			"parentPath": "",
			"pinyin": "",
			"pinyinInitial": "",
			"updated": "yyyy-MM-dd hh:mm:ss",
			"zipCode": ""
		}
	],
	"error": "",
	"msg": "",
	"page": 0,
	"pageSize": 0,
	"total": 0
}
```



### 基本数据维护接口

- `GET` `/_builtin/geo/_address`
- `GET` `/_builtin/geo/_address/{id}`
- `POST` `/_builtin/geo/_address`
- `PUT` `/_builtin/geo/_address/{id}`
- `PUT` `/_builtin/geo/_address`
- `DELETE` `/_builtin/geo/_address`
- `DELETE` `/_builtin/geo/_address/{id}`
