import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class UserDto(
    val id: Long,
    val username: String,
    val email: String
) : Serializable