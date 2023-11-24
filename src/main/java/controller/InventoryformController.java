package controller;

import dto.InventoryDto;
import dto.tm.InventoryTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InventoryModel;

import java.sql.SQLException;
import java.util.List;

public class InventoryformController {
    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmnGodownID;

    @FXML
    private TableColumn<?, ?> clmnInvoiceNumber;

    @FXML
    private TableColumn<?, ?> clmnItemID;

    @FXML
    private TableColumn<?, ?> clmnQuantity;

    @FXML
    private TableColumn<?, ?> clmnTrackID;

    @FXML
    private TableView<InventoryTable> tblInventory;

    @FXML
    private TextField txtGodownId;

    @FXML
    private TextField txtInvoiceNumber;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTrackID;

    private static InventoryDto inventoryDto=new InventoryDto();
    private static InventoryModel inventoryModel=new InventoryModel();

    public void initialize() {
        setCellValueFactory();
        loadAllInventory();
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String GodownID=txtGodownId.getText();
        String ItemID=txtItemID.getText();
        String TrackID=txtTrackID.getText();
        String InvoiceID=txtInvoiceNumber.getText();
        int Quantity= Integer.parseInt(txtQuantity.getText());

        inventoryDto=new InventoryDto(GodownID,ItemID,TrackID,InvoiceID,Quantity);
        try {
            boolean isSaved=inventoryModel.saveInventory(inventoryDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Inventory saved").show();
                setCellValueFactory();
                loadAllInventory();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();

        }


    }

    private void loadAllInventory() {
        var model = new InventoryModel();

        ObservableList<InventoryTable> obList = FXCollections.observableArrayList();

        try {
            List<InventoryDto> dtoList = model.getAllItem();

            for (InventoryDto dto : dtoList) {
                obList.add(
                        new InventoryTable(
                                dto.getGodownID(),
                                dto.getItemID(),
                                dto.getTrackID(),
                                dto.getInvoiceNum(),
                                dto.getQuantity()
                        )
                );
            }

            tblInventory.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        clmnGodownID.setCellValueFactory(new PropertyValueFactory<>("GodownID"));
        clmnItemID.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        clmnTrackID.setCellValueFactory(new PropertyValueFactory<>("TrackID"));
        clmnInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("InvoiceNum"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
