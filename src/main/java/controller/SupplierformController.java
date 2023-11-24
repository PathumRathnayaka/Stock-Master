package controller;


import dto.ItemDto;
import dto.SupplierDto;


import dto.tm.SupplierTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import model.SupplierModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.parse;

public class SupplierformController {

    private static SupplierDto supplierDto=new SupplierDto();
    private static SupplierModel supplierModel=new SupplierModel();
    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmnDate;

    @FXML
    private TableColumn<?, ?> clmnID;

    @FXML
    private TableColumn<?, ?> clmnInvoiceName;

    @FXML
    private TableColumn<?, ?> clmnItemName;

    @FXML
    private TableColumn<?, ?> clmnSupplierContact;

    @FXML
    private TableColumn<?, ?> clmnSupplierName;

    @FXML
    private TableView<SupplierTable> tblSupplier;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtInvoiceName;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtSupplierContact;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtSupplierName;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    public void initialize(){
        setCellValueFactory();
        loadAllSupplier();
        tableListener();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String SupplierID=txtSupplierID.getText();
        String SupplierName=txtSupplierName.getText();
        String InvoiceNum=txtInvoiceName.getText();
        String ItemName=txtItemName.getText();
        LocalDate Date= txtDate.getValue();
        int SupplierContact= Integer.parseInt(txtSupplierContact.getText());

        supplierDto=new SupplierDto(SupplierID,SupplierName,InvoiceNum,ItemName,Date,SupplierContact);
        try {
            boolean isSaved= supplierModel.SaveSupplier(supplierDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Suplier is saved").show();
                ClearField();
                loadAllSupplier();

            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }


    }

    private void setCellValueFactory() {
        clmnID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        clmnSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        clmnInvoiceName.setCellValueFactory(new PropertyValueFactory<>("InvoiceNum"));
        clmnItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clmnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clmnSupplierContact.setCellValueFactory(new PropertyValueFactory<>("SupplierContact"));
    }

    private void loadAllSupplier() {
        var model = new SupplierModel();

        ObservableList<SupplierTable> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDto> dtoList = model.getAllSupplier();

            for (SupplierDto dto : dtoList) {
                obList.add(
                        new SupplierTable(
                                dto.getSupplierID(),
                                dto.getSupplierName(),
                                dto.getInvoiceNum(),
                                dto.getItemName(),
                                dto.getDate(),
                                dto.getSupplierContact()
                        )
                );
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ClearField(){
        txtSupplierID.setText("");
        txtSupplierName.setText("");
        txtInvoiceName.setText("");
        txtItemName.setText("");
        txtSupplierContact.setText("");
    }
    private void tableListener() {
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }
    private void setData(SupplierTable row) {
        txtSupplierID.setText(row.getSupplierID());
        txtSupplierName.setText(row.getSupplierName());
        txtInvoiceName.setText(row.getInvoiceNum());
        txtItemName.setText(row.getItemName());
        txtSupplierContact.setText(String.valueOf(row.getSupplierContact()));
    }
    private void setFields(SupplierDto dto) {
        txtSupplierID.setText(dto.getSupplierID());
        txtSupplierName.setText(dto.getSupplierName());
        txtInvoiceName.setText(String.valueOf(dto.getInvoiceNum()));
        txtItemName.setText(dto.getItemName());
        txtSupplierContact.setText(String.valueOf(dto.getSupplierContact()));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String code = txtSupplierID.getText();

        try {
            SupplierDto dto = supplierModel.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
