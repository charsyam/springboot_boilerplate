package win.playcode.boiler.api.user.controller

import org.springframework.web.bind.annotation.*
import win.playcode.boiler.api.user.service.UserApiService

@RestController
@RequestMapping(path = ["/api/user/v1"])
class UserApiController(
    private val userApiService: UserApiService,
) {
    @GetMapping("/user/me")
    fun me(): String {
        return "API"
    }
}