package win.playcode.boiler.core.domain.user.enums

enum class UserStatus(
    var index: Int,
    var description: String,
    var display: String
) {
    REGISTERED(0, "REGISTERED", "등록"),
    UNREGISTERED(1, "UNREGISTERED", "해지"),
}