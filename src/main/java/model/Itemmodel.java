package model;

import db.DbConnection;
import dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Itemmodel {

    public boolean SaveItems(ItemDto itemDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO ITEM VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,itemDto.getId());
        preparedStatement.setString(2,itemDto.getName());
        preparedStatement.setDouble(3,itemDto.getPrice());
        preparedStatement.setString(4,itemDto.getCategory());
        preparedStatement.setString(5,itemDto.getDate());
        preparedStatement.setString(6,itemDto.getDescription());

        boolean isSaved = preparedStatement.executeUpdate() > 0;
        return isSaved;
    }

    public List<ItemDto> getAllItem() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ItemDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getString(6),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }
    void UpdateItems(){
        Connection connection= DbConnection.getInstance().getConnection();
    }

}
