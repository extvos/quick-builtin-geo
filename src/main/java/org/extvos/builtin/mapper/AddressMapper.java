package org.extvos.builtin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.extvos.builtin.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Mingcai SHEN
 */

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * Get parent path names by parent path ids.
     *
     * @param parentIds a list of id
     * @return String
     */
    String getParentPathByIds(@Param("parentIds") List<Integer> parentIds);
}
