package greencity.dto.user;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EcoNewsAuthorDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
}
