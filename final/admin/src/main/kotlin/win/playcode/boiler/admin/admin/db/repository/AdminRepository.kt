package win.playcode.boiler.admin.admin.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import win.playcode.boiler.admin.admin.db.entity.AdminEntity

@Repository
interface AdminRepository : JpaRepository<AdminEntity, Long> {
}