package kuit.springbasic.jdbc;

import kuit.springbasic.jdbc.connection.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter pstmtSetter) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String sql, Object... values) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId((int) rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            T object = null;
            if (rs.next()) {
                object = rowMapper.mapRow(rs);
            }
            return object;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper) {
        List<T> objects = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) {
        List<T> objects = new ArrayList<>();
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return objects;
    }

}
