package plus.extvos.builtin.geo.entity;

/**
 * @author Mingcai SHEN
 */

public enum Grade {
    /**
     * Province:1:省/直辖市/自治区
     */
    Province(1, "省/直辖市/自治区"),
    /**
     * City:2:市
     */
    City(2, "市"),
    /**
     * County:3:县/区
     */
    County(3, "县/区"),
    /**
     * Town:4:乡镇/街道
     */
    Town(4, "乡镇/街道"),
    /**
     * Village:5:村/社区
     */
    Village(5, "村/社区");

    private final int val;
    private final String desc;

    Grade(int v, String d) {
        val = v;
        desc = d;
    }

    public String desc() {
        return desc;
    }

    public int value() {
        return val;
    }
}
