package project.spring.dto.response;

import java.util.UUID;

public record LoginResponse (String acessToken, Long exipresIn, UUID usuarioId, String Role){

}
