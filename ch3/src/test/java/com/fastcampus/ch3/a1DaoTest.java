package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class a1DaoTest {

    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager tm;

    @Test
    public void insertTest() throws Exception {
        // TxManager 를 생성
        // 하나의 연결로 같은 트랙잭션을 통해 2개의 데이터를 주입한다.
//        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);               // 하나의 연결을 쓰기위한 DataSourceTransactionManager 를 생성
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());

        // Tx 시작
        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);       // 성공
            a1Dao.insert(2, 200);       // 실패
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {
        }
    }
}