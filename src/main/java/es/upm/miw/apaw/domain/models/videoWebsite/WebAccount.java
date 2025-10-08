package es.upm.miw.apaw.domain.models.videoWebsite;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebAccount {
    private UUID uid;
    private String userName;
    private AccountType accountType;

    @NotNull
    private UserDto user;


}
