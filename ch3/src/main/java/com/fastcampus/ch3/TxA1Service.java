package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxA1Service {
    @Autowired
    A1Dao a1Dao;

    @Autowired
    TxB1Service txB1Service;

    // Transactions propagation 부분 적용이 안되는 문제가 있었는데, Service 클래스를 2개로 분리 후 실행시키니 적용됨.
    // TxA1Service / TxB1Service 두개의 클래스로 분리. 같은 클래스내에 같은 트랜잭션 함수를 호출 시 적용이 되지 않는다.
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1WithTx() throws Exception {
        a1Dao.insert(1, 100);
        txB1Service.insertB1WithTx();
        a1Dao.insert(1, 200);
    }

    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(1, 100);       // 성공
        a1Dao.insert(1, 200);       // 실패
    }

    //    @Transactional(rollbackFor = Exception.class)     //Exception을 rollback (예외를 지정해줌)
    @Transactional      // RuntimeException, Error만 rollback
    public void insertA1WithTxFail() throws Exception {
        a1Dao.insert(1, 100);       // 성공
//        throw new RuntimeException();
//        throw new Exception();
        a1Dao.insert(1, 200);       // 실패
    }

    @Transactional
    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }
}