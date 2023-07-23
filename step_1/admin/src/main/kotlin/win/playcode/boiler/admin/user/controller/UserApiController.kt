package win.playcode.boiler.admin.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import win.playcode.boiler.core.domain.user.db.repository.UserRepository

@RestController
@RequestMapping(path = ["/api/user/v1"])
class UserApiController(
    private val userRepository: UserRepository,
) {
    @GetMapping("/user/me")
    fun me(): String {
        return "ADMIN"
    }
}