package win.playcode.boiler.core.domain.user.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import win.playcode.boiler.core.domain.user.db.entity.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
}