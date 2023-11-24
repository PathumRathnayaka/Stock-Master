package model;

import db.DbConnection;
import dto.ShimpentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipmentModel {
    public boolean SaveShipment(ShimpentDto shimpentDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO shipment VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,shimpentDto.getShipmentID());
        preparedStatement.setString(2,shimpentDto.getAddress());
        preparedStatement.setString(3, shimpentDto.getSerialNum());
        preparedStatement.setString(4, shimpentDto.getBranchNum());
        preparedStatement.setString(5, String.valueOf(shimpentDto.getDate()));

        boolean isSaved=preparedStatement.executeUpdate()>0;
        return isSaved;
    }

    public List<ShimpentDto> getAllShipments() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM shipment";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ShimpentDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new ShimpentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5).toLocalDate()
                    )
            );
        }
        return dtoList;
    }
}
