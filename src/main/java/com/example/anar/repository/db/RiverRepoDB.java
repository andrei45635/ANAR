package com.example.anar.repository.db;

import com.example.anar.domain.River;
import com.example.anar.repository.IRepository;
import com.example.anar.utils.db.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RiverRepoDB implements IRepository<Integer, River> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<River> findAll() {
        List<River> rivers = new ArrayList<>();
        String query = "SELECT * FROM rivers";

        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int riverID = resultSet.getInt("riverid");
                String riverName = resultSet.getString("rivername");
                int riverMean= resultSet.getInt("rivermean");

                River river = new River(riverID, riverName, riverMean);
                rivers.add(river);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rivers;
    }

    @Override
    public River save(River river) {
        String query = "INSERT INTO rivers(riverid, rivername, rivermean) values (?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, river.getId());
            statement.setString(2, river.getName());
            statement.setInt(3, river.getMean());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return river;
    }

    public River update(River river){
        String query = "UPDATE rivers SET rivermean = ? WHERE riverid = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, river.getMean());
            statement.setInt(2, river.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return river;
    }
}
