package win.playcode.boiler.api.user.controller

import org.springframework.web.bind.annotation.*
import win.playcode.boiler.api.common.log.Log
import win.playcode.boiler.api.user.service.UserApiService

@RestController
@RequestMapping(path = ["/api/user/v1"])
class UserApiController(
    private val userApiService: UserApiService,
) {
    companion object : Log

    @GetMapping("/user/me")
    fun me(): String {
        logger.info("me")
        return "OK"
    }
}