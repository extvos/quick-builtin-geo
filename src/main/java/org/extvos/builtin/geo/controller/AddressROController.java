package org.extvos.builtin.geo.controller;


import org.extvos.builtin.geo.entity.Grade;
import org.extvos.builtin.geo.entity.Address;
import org.extvos.builtin.geo.service.AddressService;
import org.extvos.restlet.controller.BaseROController;
import org.extvos.restlet.exception.RestletException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.extvos.restlet.Result;

/**
 * @author Mingcai SHEN
 */
@Api(tags = {"地址信息"}, value = "分级只读")
@RequestMapping(value = "/_builtin/address")
@RestController
public class AddressROController extends BaseROController<Address, AddressService> {

    private static final Logger log = LoggerFactory.getLogger(AddressROController.class);


    @Value("${quick.builtin.geo.separator:/}")
    private String pathSeparator;

    @ApiOperation(value = "省/直辖市/自治区 列表", notes = "获取列表，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/provinces")
    public Result<List<Address>> getProvinces(@RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        if (null == queries) {
            queries = new HashMap<>();
        }
        queries.put("grade", Grade.Province.value());
        return selectByMap(null, queries);
    }

    @ApiOperation(value = "省/直辖市/自治区 详情", notes = "获取单个详情，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/province/{id}")
    public Result<Address> getProvince(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        Result<Address> ra = selectById(id, queries);
        if (ra.getData().getGrade() != Grade.Province.value()) {
            throw RestletException.notFound("grade not match");
        }
        return ra;
    }

    @ApiOperation(value = "市/城市 列表", notes = "获取 省/直辖市/自治区 下，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md ")
    @GetMapping("/province/{provinceId}/cities")
    public Result<List<Address>> getCities(@PathVariable Long provinceId, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        if (null == queries) {
            queries = new HashMap<>();
        }
        queries.put("grade", Grade.City.value());
        queries.put("parentId", provinceId);
        return selectByMap(null, queries);
    }

    @ApiOperation(value = "城市详情", notes = "获取某个城市详情，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/city/{id}")
    public Result<Address> getCity(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        Result<Address> ra = selectById(id, queries);
        if (ra.getData().getGrade() != Grade.City.value()) {
            throw RestletException.notFound("grade not match");
        }
        return ra;
    }

    @ApiOperation(value = "县（区）列表", notes = "获取某个市下面的县（区），查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/city/{cityId}/counties")
    public Result<List<Address>> getCounties(@PathVariable Long cityId, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        if (null == queries) {
            queries = new HashMap<>();
        }
        queries.put("grade", Grade.County.value());
        queries.put("parentId", cityId);
        return selectByMap(null, queries);
    }

    @ApiOperation(value = "县（区）详情", notes = "获取某个县（区）的详情，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/county/{id}")
    public Result<Address> getCounty(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        Result<Address> ra = selectById(id, queries);
        if (ra.getData().getGrade() != Grade.County.value()) {
            throw RestletException.notFound("grade not match");
        }
        return ra;
    }

    @ApiOperation(value = "镇（街道）列表", notes = "获取某个县（区）下属的镇（街道），查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/county/{countyId}/towns")
    public Result<List<Address>> getTowns(@PathVariable Long countyId, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        if (null == queries) {
            queries = new HashMap<>();
        }
        queries.put("grade", Grade.Town.value());
        queries.put("parentId", countyId);
        return selectByMap(null, queries);
    }

    @ApiOperation(value = "镇（街道）详情", notes = "获取某个镇（街道）的详情，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/town/{id}")
    public Result<Address> getTown(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        Result<Address> ra = selectById(id, queries);
        if (ra.getData().getGrade() != Grade.Town.value()) {
            throw RestletException.notFound("grade not match");
        }
        return ra;
    }

    @ApiOperation(value = "村（社区）列表", notes = "获取某个镇（街道）下属的村（社区）列表，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/town/{townId}/villages")
    public Result<List<Address>> getVillages(@PathVariable Long townId, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        if (null == queries) {
            queries = new HashMap<>();
        }
        queries.put("grade", Grade.Village.value());
        queries.put("parentId", townId);
        return selectByMap(null, queries);
    }

    @ApiOperation(value = "村（社区）详情", notes = "村（社区）详情，查询条件组织，请参考： https://github.com/quickstart/java-scaffolds/quick-lib-restlet/blob/develop/README.md")
    @GetMapping("/village/{id}")
    public Result<Address> getVillage(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> queries) throws RestletException {
        Result<Address> ra = selectById(id, queries);
        if (ra.getData().getGrade() != Grade.Village.value()) {
            throw RestletException.notFound("grade not match");
        }
        return ra;
    }

    @Autowired
    private AddressService addressService;

    @Override
    public AddressService getService() {
        return addressService;
    }

    @Override
    public Address postSelect(Address address) throws RestletException {
        if (address.getParentPath() != null && !address.getParentPath().isEmpty()) {
            List<Integer> ids = new LinkedList<>();
            Arrays.asList(address.getParentPath().split("/")).forEach((String s) -> {
                ids.add(Integer.parseInt(s));
            });
            String names = getService().getParentNames(ids);
            address.setParentNames("/".equals(pathSeparator) ? names : names.replace("/", pathSeparator));
        }
        return address;
    }
}
