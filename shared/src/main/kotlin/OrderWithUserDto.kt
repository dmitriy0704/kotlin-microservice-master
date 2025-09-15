data class OrderWithUserDto(
    val orderId: Long,
    val user: UserDto,
    val amount: Double,
    val status: String
)