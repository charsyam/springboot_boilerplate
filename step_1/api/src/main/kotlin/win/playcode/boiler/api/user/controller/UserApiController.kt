package win.playcode.boiler.api.user.controller

import org.springframework.web.bind.annotation.*
import win.playcode.boiler.core.domain.user.db.repository.UserRepository

@RestController
@RequestMapping(path = ["/api/user/v1"])
class UserApiController(
    private val userRepository: UserRepository,
) {
    @GetMapping("/user/me")
    fun me(): String {
        return "API"
    }
}