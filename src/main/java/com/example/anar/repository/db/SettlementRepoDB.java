package com.example.anar.repository.db;

import com.example.anar.domain.Settlement;
import com.example.anar.repository.IRepository;
import com.example.anar.utils.db.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettlementRepoDB implements IRepository<Integer, Settlement> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Settlement> findAll() {
        List<Settlement> settlements = new ArrayList<>();
        String query = "SELECT * FROM settlements";

        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int settlementID = resultSet.getInt("settlementid");
                String settlementName = resultSet.getString("settlementname");
                int riverID = resultSet.getInt("riverid");
                int cmdr = resultSet.getInt("cmdr");
                int cma = resultSet.getInt("cma");

                Settlement settlement = new Settlement(settlementID, settlementName, riverID, cmdr, cma);
                settlements.add(settlement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return settlements;
    }

    @Override
    public Settlement save(Settlement settlement) {
        String query = "INSERT INTO settlements(settlementid, settlementname, riverid, cmdr, cma) values (?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, settlement.getId());
            statement.setString(2, settlement.getName());
            statement.setInt(3, settlement.getRiverID());
            statement.setInt(4, settlement.getCmdr());
            statement.setInt(5, settlement.getCma());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return settlement;
    }
}
