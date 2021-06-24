package plus.extvos.builtin.geo.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.builtin.geo.entity.Address;
import plus.extvos.builtin.geo.service.AddressService;
import plus.extvos.restlet.QuerySet;
import plus.extvos.restlet.controller.BaseController;
import plus.extvos.restlet.exception.RestletException;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Mingcai SHEN
 */
@Api(tags = {"地址管理"}, value = "读写")
@RequestMapping(value = "/_builtin/geo/_address")
@RestController
@ConditionalOnProperty(prefix = "quick.builtin.geo", name = "full-access", havingValue = "true")
public class AddressRWController extends BaseController<Address, AddressService> {
    @Autowired
    private AddressService addressService;

    @Override
    public AddressService getService() {
        return addressService;
    }

    @Override
    public Address preUpdate(Serializable id, Address entity) throws RestletException {
        entity.setUpdated(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    @Override
    public Address preUpdate(QuerySet<Address> qs, Address entity) throws RestletException {
        entity.setUpdated(new Timestamp(System.currentTimeMillis()));
        return entity;
    }
}
