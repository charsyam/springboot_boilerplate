package win.playcode.boiler.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan("win.playcode.boiler")
@EnableJpaRepositories("win.playcode.boiler")
@SpringBootApplication(scanBasePackages = ["win.playcode.boiler"])
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
