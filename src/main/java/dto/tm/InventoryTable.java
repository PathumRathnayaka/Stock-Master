package dto.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryTable {
    private String GodownID;
    private String ItemID;
    private String TrackID;
    private String InvoiceNum;
    private int Quantity;
}
