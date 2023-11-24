package controller;

import dto.ShimpentDto;
import dto.tm.ShipmentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ShipmentModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ShipmentformController {
    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmnAddress;

    @FXML
    private TableColumn<?, ?> clmnBranchNumber;

    @FXML
    private TableColumn<?, ?> clmnDate;

    @FXML
    private TableColumn<?, ?> clmnSerialNumber;

    @FXML
    private TableColumn<?, ?> clmnShipmentID;

    @FXML
    private TableView<ShipmentTable> tblShipment;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBranchNumber;

    @FXML
    private DatePicker txtDatePicker;

    @FXML
    private TextField txtSerial;

    @FXML
    private TextField txtShipmentID;

    private static ShimpentDto shimpentDto=new ShimpentDto();
    private static ShipmentModel shipmentModel=new ShipmentModel();

    public void initialize(){
        setCellValueFactory();
        loadAllShipment();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String ShipmentID=txtShipmentID.getText();
        String Address=txtAddress.getText();
        String SerialNum=txtSerial.getText();
        String BranchNUm=txtBranchNumber.getText();
        LocalDate Date=txtDatePicker.getValue();
        shimpentDto=new ShimpentDto(ShipmentID,Address,SerialNum,BranchNUm,Date);
        try {
            boolean isSaved= shipmentModel.SaveShipment(shimpentDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Shipment is Saved").show();
                setCellValueFactory();
                loadAllShipment();
            }
        } catch (SQLException e) {
            //
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();

        }
    }
    private void loadAllShipment() {
        var model = new ShipmentModel();

        ObservableList<ShipmentTable> obList = FXCollections.observableArrayList();

        try {
            List<ShimpentDto> dtoList = model.getAllShipments();

            for (ShimpentDto dto : dtoList) {
                obList.add(
                        new ShipmentTable(
                                dto.getShipmentID(),
                                dto.getAddress(),
                                dto.getSerialNum(),
                                dto.getBranchNum(),
                                dto.getDate()
                        )
                );
            }

            tblShipment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        clmnShipmentID.setCellValueFactory(new PropertyValueFactory<>("ShipmentID"));
        clmnAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        clmnSerialNumber.setCellValueFactory(new PropertyValueFactory<>("SerialNum"));
        clmnBranchNumber.setCellValueFactory(new PropertyValueFactory<>("BranchNum"));
        clmnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
