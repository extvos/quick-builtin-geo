package plus.extvos.builtin.geo.entity;

//import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Mingcai SHEN
 */

@TableName("builtin_addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.NONE)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String zipCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cityCode;

    private String name;

    private Double lat;

    private Double lng;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long parentId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String parentPath;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private String parentNames;

    public String getParentNames() {
        return parentNames;
    }

    public void setParentNames(String parentNames) {
        this.parentNames = parentNames;
    }

    /**
     * Grade: Address Types
     * 1: Province, 2: City, 3: District, 4: Town, 5: Village, 6: Group, 7: Building
     * 0: Unknown
     *
     * @see Grade
     */
    private Integer grade;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String pinyin;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String pinyinInitial;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String borders;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Timestamp updated;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyinInitial() {
        return pinyinInitial;
    }

    public void setPinyinInitial(String pinyinInitial) {
        this.pinyinInitial = pinyinInitial;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
