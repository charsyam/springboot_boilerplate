package win.playcode.boiler.api.common.config.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * swagger config
 */
@Configuration
@OpenAPIDefinition(
    servers = [
    ]
)
class SwaggerConfig {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi
            .builder()
            .group("boiler api")
            .pathsToMatch("/**")
            .build()
    }
}
