-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE IF NOT EXISTS builtin_addresses (
	id BIGINT NOT NULL ,
	name VARCHAR(32) NOT NULL ,
	grade INT NULL DEFAULT 0  ,
	city_code VARCHAR(12) NOT NULL DEFAULT '' ,
	zip_code VARCHAR(12) NOT NULL DEFAULT '' ,
	lat DOUBLE PRECISION NULL DEFAULT 0.0  ,
	lng DOUBLE PRECISION NULL DEFAULT 0.0  ,
	parent_id BIGINT NOT NULL DEFAULT 0  ,
	parent_path VARCHAR(255) NOT NULL DEFAULT '' ,
	pinyin VARCHAR(128) NOT NULL DEFAULT '' ,
	pinyin_initial VARCHAR(16) NOT NULL DEFAULT '' ,
	borders TEXT NULL DEFAULT NULL ,
	comment VARCHAR(255) NOT NULL DEFAULT '' ,
	updated TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP  ,
	PRIMARY KEY (id)
)
;
COMMENT ON TABLE builtin_role_permissions IS '内置地址数据';
CREATE INDEX builtin_addresses_idx_grade ON builtin_addresses (grade);
CREATE INDEX builtin_addresses_idx_parent_id ON builtin_addresses (parent_id);
CREATE INDEX builtin_addresses_idx_city_code ON builtin_addresses (city_code);
CREATE INDEX builtin_addresses_idx_name ON builtin_addresses (name);
CREATE INDEX builtin_addresses_idx_pinyin ON builtin_addresses (pinyin);
CREATE INDEX builtin_addresses_idx_pinyin_initial ON builtin_addresses (pinyin_initial);
