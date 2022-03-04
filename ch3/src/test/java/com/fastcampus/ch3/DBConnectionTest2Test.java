package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

    public class DBConnectionTest2Test {
        @Autowired
        DataSource ds;

        // Test 코드는 여러번 돌려도 성공하게 만들어야한다. DataSource를 공유하지 않기 때문 (각 Test 메서드가 초기화후 별개로 돌아간다)
        @Test
        public void springJdbcConnectionTest() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

            Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

            System.out.println("conn = " + conn);
            assertTrue(conn!=null);     // Test의 성공여부를 assertTrue를 통해 확인한다. (괄호 아느이 조건식이 true면, 테스트 성공 / 아니면 실패)
        }

        @Test
        public void insertUserTest() throws Exception {
            deleteAll();
            User user = new User("asdf", "1234", "박상민", "abc@abc.com", new Date(), "fb", new Date());
            int rowCnt = insertUser(user);

            assertTrue(rowCnt == 1);
        }

        @Test
        public void selectUserTest() throws Exception {
            deleteAll();
            User user = new User("asdf", "1234", "박상민", "abc@abc.com", new Date(), "fb", new Date());
            int rowCnt = insertUser(user);
            User user2 = selectUser("asdf");

            assertTrue(user.getId().equals("asdf"));
        }

        @Test
        public void deleteUserTest() throws Exception {
            deleteAll();
            int rowCnt = deleteUser("asdf");
            assertTrue(rowCnt == 0);

            User user = new User("asdf", "1234", "박상민", "abc@abc.com", new Date(), "fb", new Date());
            rowCnt = insertUser(user);
            assertTrue(rowCnt == 1);

            rowCnt = deleteUser(user.getId());
            assertTrue(rowCnt == 1);

            assertTrue(selectUser(user.getId()) == null);
        }

        @Test
        public void updateUserTest() throws Exception {
            deleteAll();
            User user = new User("asdf", "1234", "박상민", "abc@abc.com", new Date(), "fb", new Date());
            int rowCnt = insertUser(user);
            assertTrue(rowCnt == 1);

            user = new User("asdf", "4321", "박상민", "abc@abc.com", new Date(), "fb", new Date());
            rowCnt = updateUser(user);
            assertTrue(rowCnt == 1);
        }

        @Test
        public void transactionTest() throws Exception {
            Connection conn = null;
            try {
                deleteAll();
                conn = ds.getConnection();
                conn.setAutoCommit(false);      // AutoCommit OFF

                String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now()) ";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "asdf");
                pstmt.setString(2, "1234");
                pstmt.setString(3, "abc");
                pstmt.setString(4, "aaa@aaa.com");
                pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
                pstmt.setString(6, "fb");

                int rowCnt = pstmt.executeUpdate();

                pstmt.setString(1, "asdf");         // 같은 ID가 들어가기 때문에 예외가 발생
                rowCnt = pstmt.executeUpdate();

                conn.commit();
            } catch (Exception e) {
                conn.rollback();            // 문제가 생기면 ROLLBACK
                e.printStackTrace();
            } finally {
            }
        }

        // 사용자 정보를 user_info 테이블에 저장하는 메서드
        public int insertUser(User user) throws Exception {
            Connection conn = ds.getConnection();

            //insert into user_info values ()

            String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now()) ";     // SQL Injection 공격 방지, 성능향상

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPwd());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
            pstmt.setString(6, user.getSns());

            int rowCnt = pstmt.executeUpdate();         // insert, delete, update 때 사용한다.

            return rowCnt;
        }

        // user_info 테이블의 사용자 정보 컬럼을 모두 삭제하는 메서드
        private void deleteAll() throws Exception {
            Connection conn = ds.getConnection();

            String sql = "delete from user_info ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        }

        // id 에 해당하는 사용자 정보 컬럼을 조회하여 반환하는 메서드
        public User selectUser(String id) throws Exception {
            Connection conn = ds.getConnection();

            String sql = "select * from user_info where id= ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();        // select

            if (rs.next()){
                User user = new User();
                user.setId(rs.getString(1));
                user.setPwd(rs.getString(2));
                user.setName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setBirth(new Date(rs.getDate(5).getTime()));
                user.setSns(rs.getString(6));
                user.setReg_date(new Date(rs.getDate(7).getTime()));

                return user;
            }
            return null;
        }

        public int deleteUser(String id) throws Exception {
            Connection conn = ds.getConnection();

            String sql = "delete from user_info where id= ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            return pstmt.executeUpdate();
        }

        // 매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
        public int updateUser(User user) throws Exception {
            Connection conn = ds.getConnection();

            //insert into user_info values ()

            String sql = "update user_info set pwd= ?, name= ?, email= ?, birth= ?, sns= ?, reg_date= now() where id= ?";     // SQL Injection 공격 방지, 성능향상

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPwd());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
            pstmt.setString(5, user.getSns());
            pstmt.setString(6, user.getId());

            int rowCnt = pstmt.executeUpdate();         // insert, delete, update 때 사용한다.

            return rowCnt;
        }

    }