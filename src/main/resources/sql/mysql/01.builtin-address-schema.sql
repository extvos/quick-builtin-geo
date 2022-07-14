CREATE TABLE IF NOT EXISTS `builtin_addresses` (
	`id` BIGINT(20) NOT NULL COMMENT '主键ID，与国标匹配',
	`name` VARCHAR(64) NOT NULL COMMENT '行政区名称' COLLATE 'utf8mb4_general_ci',
	`grade` INT(11) NULL DEFAULT 0 COMMENT '级别： 1=省，2=市，3=县，4=镇，5=乡' ,
	`city_code` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '城市编码' COLLATE 'utf8mb4_general_ci',
	`adcode` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '区域编码' COLLATE 'utf8mb4_general_ci',
	`zip_code` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '邮政编码' COLLATE 'utf8mb4_general_ci',
	`lat` DOUBLE NULL DEFAULT 0.0 COMMENT '中心坐标latitude' ,
	`lng` DOUBLE NULL DEFAULT 0.0 COMMENT '中心坐标longitude' ,
	`parent_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '父节点ID' ,
	`parent_path` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '所有父节点路径' COLLATE 'utf8mb4_general_ci',
	`pinyin` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '拼音' COLLATE 'utf8mb4_general_ci',
	`pinyin_initial` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '拼音首字母' COLLATE 'utf8mb4_general_ci',
	`borders` TEXT NULL DEFAULT NULL COMMENT '边界坐标(lat,lng),GEOJSON' COLLATE 'utf8mb4_general_ci',
	`comment` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注信息' COLLATE 'utf8mb4_general_ci',
	`updated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间' ,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `grade` (`grade`) USING BTREE,
	INDEX `parent_id` (`parent_id`) USING BTREE,
	INDEX `city_code` (`city_code`) USING BTREE,
	INDEX `adcode` (`adcode`) USING BTREE,
	INDEX `name` (`name`) USING BTREE,
	INDEX `pinyin` (`pinyin`) USING BTREE,
	INDEX `pinyin_initial` (`pinyin_initial`) USING BTREE
)
COMMENT='中华人民共和国行政区域地址表'
COLLATE='utf8mb4_general_ci';
