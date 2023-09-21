package plus.extvos.builtin.geo.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "quick.lbs.baidu", name = "enabled", havingValue = "true", matchIfMissing = false)
public class BaiduLbsService {
}
