package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class A1Dao {
    @Autowired
    DataSource ds;

    public int insert(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DataSourceUtils.getConnection(ds);
            System.out.println("conn = " + conn);
            pstmt = conn.prepareStatement("insert into  a1 values(?, ?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();                // 여기서 예외를 잡아먹기 때문에 a1DaoTest까지 도달하지 않는다. throw e 를 통해 예외를 전달해야함.
        } finally {
//            close(conn, pstmt);
            close(pstmt);
            DataSourceUtils.releaseConnection(conn, ds);
        }
    }

    public void deleteAll() throws  Exception {
        Connection conn = ds.getConnection();
        System.out.println("conn = " + conn);
        String sql = "delete from a1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        close(conn, pstmt);
    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}
