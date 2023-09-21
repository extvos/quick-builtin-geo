package plus.extvos.builtin.geo.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.beust.jcommander.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import plus.extvos.builtin.geo.dto.AddrInfo;
import plus.extvos.builtin.geo.dto.Coordinate;
import plus.extvos.common.utils.QuickHash;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "quick.lbs.tencent", name = "enabled", havingValue = "true", matchIfMissing = false)
public class TencentLbsService {
    @Value("${quick.lbs.tencent.key}")
    private String key;
    @Value("${quick.lbs.tencent.secret:}")
    private String secret;
    @Value("${quick.lbs.tencent.endpoint:https://apis.map.qq.com}")
    private String endpoint;

    private static final Logger log = LoggerFactory.getLogger(TencentLbsService.class);

    private String sign(String uri, Map<String, String> params) {
        log.debug("sign:> {} / {}", uri, params);
        if (secret == null || secret.isEmpty()) {
            return null;
        }
        TreeMap<String, String> tm = null != params ? new TreeMap<>(params) : new TreeMap<>();
        tm.put("key", key);
        StringBuilder sb = new StringBuilder(uri);
        sb.append("?");
        String ps = Strings.join("&", tm.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.toList()));
        sb.append(ps);
        log.debug("sign:> ss = {} ", sb);
        return QuickHash.md5().hash(sb.toString() + secret).hex();
    }

    private String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    private String buildUrl(String uri, Map<String, String> params) {
        log.debug("buildUrl:> {} / {}", uri, params);
        StringBuilder sb = new StringBuilder(endpoint);
        sb.append(uri);
        sb.append("?");
        TreeMap<String, String> tm = null != params ? new TreeMap<>(params) : new TreeMap<>();
        tm.put("key", key);
        String sig = sign(uri, params);
        log.debug("buildUrl:> sig = {}", sig);
        if (null != sig && !sig.isEmpty()) {
            tm.put("sig", sig);
        }
        String ps = Strings.join("&", tm.entrySet().stream().map(e -> e.getKey() + "=" + encode(e.getValue())).collect(Collectors.toList()));
        sb.append(ps);
        return sb.toString();
    }

    private JSONObject request(String url) {
        HttpResponse resp = HttpRequest.get(url).execute();
        if (null == resp) {
            log.warn("request:> null response");
            return null;
        } else if (!resp.isOk()) {
            log.warn("request:> non-ok response: {} / {}", resp.getStatus(), resp.body());
            return null;
        }
        return new JSONObject(resp.body());
    }

    public String coordinate2address(double lat, double lng) {
        Map<String, String> params = new HashMap<>();
        params.put("location", lat + "," + lng);
        String url = buildUrl("/ws/geocoder/v1", params);
        JSONObject jb = request(url);
        log.debug("coordinate2address:> jb = {}", jb);
        if (null == jb) {
            return "";
        }
        if ((int) jb.get("status") != 0) {
            return "";
        }
        if (!jb.containsKey("result")) {
            return "";
        }
        jb = (JSONObject) jb.get("result");
        if (!jb.containsKey("address")) {
            return "";
        }
        return jb.getStr("address");
    }

    public String coordinate2address(Coordinate coord) {
        return coordinate2address(coord.getLat(), coord.getLng());
    }

    public AddrInfo coordinate2addrInfo(double lat, double lng) {
        AddrInfo info = new AddrInfo();
        Map<String, String> params = new HashMap<>();
        params.put("location", lat + "," + lng);
        String url = buildUrl("/ws/geocoder/v1", params);
        JSONObject jb = request(url);
        log.debug("coordinate2addrInfo:> jb = {}", jb);
        if (null == jb) {
            return null;
        }
        if ((int) jb.get("status") != 0) {
            return null;
        }
        if (!jb.containsKey("result")) {
            return null;
        }
        jb = (JSONObject) jb.get("result");
        if (jb.containsKey("address")) {
            info.setAddress(jb.getStr("address"));
        }
        if (jb.containsKey("ad_info")) {
            JSONObject jbb = (JSONObject) jb.get("ad_info");
            info.setProvince(jbb.getStr("province"));
            info.setCity(jbb.getStr("city"));
            info.setCounty(jbb.getStr("district"));
            info.setAdcode(jbb.getStr("adcode"));
        }
        info.setLat(lat).setLng(lng);
        return info;
    }

    public AddrInfo coordinate2addrInfo(Coordinate coord) {
        return coordinate2addrInfo(coord.getLat(), coord.getLng());
    }

    public Coordinate address2coordinate(String addr) {
        Map<String, String> params = new HashMap<>();
        params.put("address", addr);
        String url = buildUrl("/ws/geocoder/v1", params);
        JSONObject jb = request(url);
        log.debug("address2coordinate:> jb = {}", jb);
        if (null == jb) {
            return null;
        }
        if ((int) jb.get("status") != 0) {
            return null;
        }
        if (!jb.containsKey("result")) {
            return null;
        }
        jb = (JSONObject) jb.get("result");
        if (!jb.containsKey("location")) {
            return null;
        }
        jb = (JSONObject) jb.get("location");
        return new Coordinate(
                jb.getDouble("lat"),
                jb.getDouble("lng")
        );
    }
}
