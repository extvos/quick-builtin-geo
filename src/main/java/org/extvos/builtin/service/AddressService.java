package org.extvos.builtin.service;

import org.extvos.builtin.entity.Address;
import org.extvos.restlet.service.BaseService;

import java.util.List;

/**
 * @author Mingcai SHEN
 */
public interface AddressService extends BaseService<Address> {
    /**
     * get parent names with given ids
     *
     * @param parentIds a list of id
     * @return string of names separated with ' '
     */
    String getParentNames(List<Integer> parentIds);
}
