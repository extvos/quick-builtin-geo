package plus.extvos.builtin.geo.dto;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private Double lat;

    private Double lng;

    public Coordinate() {
//        this.lat = 0.0;
//        this.lng = 0.0;
    }

    public Coordinate(String loc) {
        if (null == loc || loc.isEmpty()) {
            return;
        }
        String[] ls = loc.split(",", 2);
        if (ls.length != 2) {
            return;
        }
        this.lat = Double.parseDouble(ls[0]);
        this.lng = Double.parseDouble(ls[1]);
    }

    public Coordinate(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public Coordinate setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public Coordinate setLng(Double lng) {
        this.lng = lng;
        return this;
    }
}
