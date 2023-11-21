package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainformController {

    public Button btnItem;
    public AnchorPane SubAnchorPane;
    public Button btnInventory;
    public Button btnOrders;
    public Button btnSupplier;
    public Button btnShipment;
    public Button btnExpired;
    public Button btnDashBoard;

    public void initialize() throws IOException {
        InitializeDashBoard();
    }

    public void InitializeDashBoard() throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/DashBoardform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }


    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent node =FXMLLoader.load(this.getClass().getResource("/view/Itemform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent node =FXMLLoader.load(this.getClass().getResource("/view/Inventoryform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Ordersform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Supplierform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnShipmentOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Shipmentform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnExpiredOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/ExpiredItemform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        InitializeDashBoard();
    }
}
