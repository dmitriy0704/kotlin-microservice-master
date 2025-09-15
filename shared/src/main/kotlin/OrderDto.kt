import com.fasterxml.jackson.annotation.JsonProperty

// DTO для заказа
data class OrderDto(
    val id: Long,
    val userId: Long,
    val amount: Double,
    val status: String
)