package plus.extvos.builtin.geo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Mingcai SHEN
 */
@EntityScan("plus.extvos.builtin.geo.entity")
@MapperScan("plus.extvos.builtin.geo.mapper")
@ComponentScan(basePackages = "plus.extvos.builtin.geo")
public class BuiltinAutoConfigure {
    @Bean
    @ConditionalOnProperty(prefix = "spring.swagger", name = "disabled", havingValue = "false", matchIfMissing = true)
    public Docket createGeoDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("地址信息服务")
            .apiInfo(new ApiInfoBuilder()
                .title("地址信息服务")
                .description("Builtin GEO services for generic use.")
                .contact(new Contact("Mingcai SHEN", "https://github.com/archsh/", "archsh@gmail.com"))
                .termsOfServiceUrl("https://github.com/extvos/quickstart/raw/develop/LICENSE")
                .version(getClass().getPackage().getImplementationVersion())
                .build())
            .select()
            .apis(RequestHandlerSelectors.basePackage("plus.extvos.builtin.geo"))
            .build();
    }
}
