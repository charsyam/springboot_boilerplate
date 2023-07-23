package win.playcode.boiler.core.domain.user.db.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "`user`")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,
    var uid: String? = null,

    var lastLoginAt: LocalDateTime? = null,
    var lastAccessedAt: LocalDateTime? = null,
    var profileImageUrl: String? = null,
    var createdAt: LocalDateTime? = null,
    var createdBy: String? = null,
    var updatedAt: LocalDateTime? = null,
    var updatedBy: String? = null,
)