package plus.extvos.builtin.geo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.extvos.builtin.geo.entity.Address;
import plus.extvos.builtin.geo.mapper.AddressMapper;
import plus.extvos.builtin.geo.service.AddressService;
import plus.extvos.restlet.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * @author Mingcai SHEN
 */

@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper myMapper;

    @Override
    public AddressMapper getMapper() {
        return myMapper;
    }

    @Override
    public String getParentNames(List<Integer> parentIds) {
        return getMapper().getParentPathByIds(parentIds);
    }


}
