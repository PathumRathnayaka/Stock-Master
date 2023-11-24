package dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private String SupplierID;
    private String SupplierName;
    private String InvoiceNum;
    private String ItemName;
    private LocalDate Date;
    private int SupplierContact;
}
