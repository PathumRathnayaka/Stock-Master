package controller;

import dto.ItemDto;
import dto.tm.ItemTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Itemmodel;
import util.Regex;
import util.TextFields;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ItemformController {

    public AnchorPane root;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;


    @FXML
    private TableColumn<?, ?> clmnCategory;

    @FXML
    private TableColumn<?, ?> clmnDate;

    @FXML
    private TableColumn<?, ?> clmnDescription;

    @FXML
    private TableColumn<?, ?> clmnID;

    @FXML
    private TableColumn<?, ?> clmnName;

    @FXML
    private TableColumn<?, ?> clmnPrice;

    @FXML
    private TableView<ItemTable> tblItems;

    private static ItemDto itemDto=new ItemDto();
    private static Itemmodel itemmodel=new Itemmodel();
    //private static ItemTable itemTable=new ItemTable();

    public void initialize() {
        setCellValueFactory();
        loadAllItem();
        tableListener();
    }

    private void setCellValueFactory() {
        clmnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        clmnCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        clmnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clmnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean validate = validate();
        if (!validate){
            new Alert(Alert.AlertType.WARNING,"Fill ALl Fields With Valid Data").show();
            return;
        }


        String id=txtID.getText();
        String name=txtName.getText();
        double price= Double.parseDouble(txtPrice.getText());
        String description=txtDescription.getText();
        String category=txtCategory.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());

         itemDto =new ItemDto(id,name,price,description,category,date);
        try {
            boolean isSaved=itemmodel.SaveItems(itemDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item is Saved").show();
                ClearField();
                loadAllItem();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String code = txtID.getText();

        try {
            ItemDto dto = itemmodel.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    private void loadAllItem() {
        var model = new Itemmodel();

        ObservableList<ItemTable> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = model.getAllItem();

            for (ItemDto dto : dtoList) {
                obList.add(
                        new ItemTable(
                                dto.getId(),
                                dto.getName(),
                                dto.getPrice(),
                                dto.getCategory(),
                                dto.getDate(),
                                dto.getDescription()
                        )
                );
            }

            tblItems.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void ClearField(){
        txtID.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCategory.setText("");
        txtDate.setText("");
        txtDescription.setText("");
    }
    private void tableListener() {
        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }
    private void setData(ItemTable row) {
        txtID.setText(row.getId());
        txtName.setText(row.getName());
        txtPrice.setText(String.valueOf(row.getPrice()));
        txtCategory.setText(row.getCategory());
        txtDate.setText(String.valueOf(row.getDate()));
        txtDescription.setText(row.getDescription());
    }
    private void setFields(ItemDto dto) {
        txtID.setText(dto.getId());
        txtName.setText(dto.getName());
        txtPrice.setText(String.valueOf(dto.getPrice()));
        txtCategory.setText(dto.getCategory());
        txtDate.setText(String.valueOf(dto.getDate()));
        txtDescription.setText(dto.getDescription());
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ID,txtID);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void txtPriceOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtPrice);
    }

    public void txtCategoryOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtDescriptionOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtDateOnKeyReleased(KeyEvent keyEvent) {

    }


    public boolean validate(){
        return Regex.setTextColor(TextFields.ID,txtID) &&
                Regex.setTextColor(TextFields.NAME,txtName)&&
                Regex.setTextColor(TextFields.DOUBLE,txtPrice);

    }

}
