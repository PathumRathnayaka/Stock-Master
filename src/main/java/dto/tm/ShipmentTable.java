package dto.tm;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentTable {
    private String ShipmentID;
    private String Address;
    private String SerialNum;
    private String BranchNum;
    private LocalDate Date;
}
