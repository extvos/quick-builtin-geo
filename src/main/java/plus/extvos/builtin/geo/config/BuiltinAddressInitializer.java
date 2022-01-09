package plus.extvos.builtin.geo.config;

import com.baomidou.mybatisplus.annotation.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import plus.extvos.builtin.geo.entity.Address;
import plus.extvos.restlet.utils.DatabaseHelper;

import javax.sql.DataSource;

/**
 * @author Mingcai SHEN
 */
@Component
public class BuiltinAddressInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(BuiltinAddressInitializer.class);

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("BuiltinAddressInitializer::run:> ...");
        DatabaseHelper dh = DatabaseHelper.with(dataSource);
        if (dh.tableAbsent(Address.class.getAnnotation(TableName.class).value()) > 0) {
            dh.runScriptsIfMySQL("sql/mysql/01.builtin-address-schema.sql", "sql/mysql/02.builtin-address-data.sql");
            dh.runScriptsIfPostgreSQL("sql/pg/01.builtin-address-schema.sql", "sql/pg/02.builtin-address-data.sql");
        }
    }
}
