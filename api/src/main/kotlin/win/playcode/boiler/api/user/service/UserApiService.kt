package win.playcode.boiler.api.user.service

import org.springframework.stereotype.Service
import win.playcode.boiler.core.domain.user.db.repository.UserRepository

@Service
class UserApiService(
    private val userRepository: UserRepository,
) {

}