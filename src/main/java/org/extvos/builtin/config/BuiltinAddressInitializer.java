package org.extvos.builtin.config;

import org.extvos.restlet.utils.SpringContextHolder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;

/**
 * @author Mingcai SHEN
 */
@Component
public class BuiltinAddressInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BuiltinAddressInitializer.class);

    @Autowired
    DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        log.info("BuiltinAddressInitializer::run:> ...");
        Connection conn = dataSource.getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(dataSource.getLogWriter());
        String[] sqlFiles = {"sql/01.builtin-address-schema.sql", "sql/02.builtin-address-data.sql"};
        for (String path : sqlFiles) {
            Reader reader = Resources.getResourceAsReader(path);
            ;
            //执行SQL脚本
            runner.runScript(reader);
            //关闭文件输入流
            reader.close();
        }
    }
}
