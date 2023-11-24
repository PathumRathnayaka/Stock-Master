package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDto {
    private String GodownID;
    private String ItemID;
    private String TrackID;
    private String InvoiceNum;
    private int Quantity;
}
