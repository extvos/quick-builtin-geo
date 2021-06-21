package plus.extvos.builtin.geo.service;

import plus.extvos.builtin.geo.entity.Address;
import plus.extvos.restlet.service.BaseService;

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
