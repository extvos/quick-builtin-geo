package plus.extvos.builtin.geo.dto;

import java.io.Serializable;

public class AddrInfo implements Serializable {
    private String address;
    private Double lat;

    private Double lng;

    private String province;

    private String city;

    private String county;

    private String town;

    private String adcode;

    public String getAddress() {
        return address;
    }

    public AddrInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public AddrInfo setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public AddrInfo setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public AddrInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddrInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCounty() {
        return county;
    }

    public AddrInfo setCounty(String county) {
        this.county = county;
        return this;
    }

    public String getTown() {
        return town;
    }

    public AddrInfo setTown(String town) {
        this.town = town;
        return this;
    }

    public String getAdcode() {
        return adcode;
    }

    public AddrInfo setAdcode(String adcode) {
        this.adcode = adcode;
        return this;
    }
}
