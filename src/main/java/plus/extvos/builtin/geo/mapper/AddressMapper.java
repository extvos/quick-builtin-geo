package plus.extvos.builtin.geo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import plus.extvos.builtin.geo.entity.Address;

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
    List<String> getParentPathByIds(@Param("parentIds") List<Integer> parentIds);
}
