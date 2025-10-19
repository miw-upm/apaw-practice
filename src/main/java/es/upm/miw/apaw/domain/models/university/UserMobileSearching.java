package es.upm.miw.apaw.domain.models.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMobileSearching {
    private List<String> mobiles;
}
