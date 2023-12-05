package controller;

import db.DbConnection;
import dto.MarketPlaceDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.MarketPlacemodel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class MarketPlaceformController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmnAddress;

    @FXML
    private TableColumn<?, ?> clmnEmail;

    @FXML
    private TableColumn<?, ?> clmnHotline;

    @FXML
    private TableColumn<?, ?> clmnMarketID;

    @FXML
    private TableColumn<?, ?> clmnMarketName;

    @FXML
    private TableView<?> tblOrders;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHotline;

    @FXML
    private TextField txtMarketID;

    @FXML
    private TextField txtMarketName;

    private static MarketPlaceDto marketPlaceDto=new MarketPlaceDto();
    private static MarketPlacemodel marketPlacemodel=new MarketPlacemodel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String marketPlaceId=txtMarketID.getText();
        String marketName=txtMarketName.getText();
        String address=txtAddress.getText();
        int hotline= Integer.parseInt(txtHotline.getText());
        String emai=txtEmail.getText();

        marketPlaceDto=new MarketPlaceDto(marketPlaceId,marketName,address,hotline,emai);
        try {
            boolean isSaved=marketPlacemodel.SaveMarket(marketPlaceDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Market is Saved !").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void btnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/stockmaster Report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasprePrint = JasperFillManager.fillReport(jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasprePrint,false);
    }
}
