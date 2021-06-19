package org.extvos.builtin.geo.service.impl;

import org.extvos.builtin.geo.entity.Address;
import org.extvos.builtin.geo.mapper.AddressMapper;
import org.extvos.builtin.geo.service.AddressService;
import org.extvos.restlet.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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