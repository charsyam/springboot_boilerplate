package win.playcode.boiler.admin.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import win.playcode.boiler.admin.common.log.Log
import win.playcode.boiler.admin.user.service.UserApiService

@RestController
@RequestMapping(path = ["/api/user/v1"])
class UserApiController(
    private val userApiService: UserApiService,
) {
    companion object : Log

    @GetMapping("/user/me")
    fun me(): String {
        logger.info("me")
        return "admin"
    }
}