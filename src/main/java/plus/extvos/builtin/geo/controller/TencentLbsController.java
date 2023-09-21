package plus.extvos.builtin.geo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.builtin.geo.dto.AddrInfo;
import plus.extvos.builtin.geo.dto.Coordinate;
import plus.extvos.builtin.geo.service.impl.TencentLbsService;
import plus.extvos.common.Result;
import plus.extvos.common.exception.ResultException;

@Api(tags = {"腾讯LBS服务"}, value = "腾讯地图位置服务，来源WebService API")
@RequestMapping(value = "/_builtin/address/tencent")
@RestController
@ConditionalOnProperty(prefix = "quick.lbs.tencent", name = "enabled", havingValue = "true", matchIfMissing = false)
public class TencentLbsController {
    @Autowired
    private TencentLbsService lbsService;

    @ApiOperation(value = "坐标转地址", notes = "通过经纬度坐标获取地址，单独地址返回")
    @GetMapping("/coor2addr")
    public Result<String> coor2addr(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng) throws ResultException {
        String s = lbsService.coordinate2address(lat, lng);
        return Result.data(s).success();
    }

    @ApiOperation(value = "坐标转地址", notes = "通过经纬度坐标获取地址，附加信息返回")
    @GetMapping("/coor2info")
    public Result<AddrInfo> coor2info(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng) throws ResultException {
        AddrInfo s = lbsService.coordinate2addrInfo(lat, lng);
        return Result.data(s).success();
    }

    @ApiOperation(value = "地址转坐标", notes = "通过地址获取经纬度坐标")
    @GetMapping("/addr2coor")
    public Result<Coordinate> addr2coor(@RequestParam("addr") String addr) throws ResultException {
        Coordinate s = lbsService.address2coordinate(addr);
        return Result.data(s).success();
    }
}
