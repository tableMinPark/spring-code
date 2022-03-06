package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.*;

import java.util.*;

public interface BoardDao {
    int count() throws Exception;
    int deleteAll() throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    int insert(BoardDto dto) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    BoardDto select(Integer bno) throws Exception;
    List<BoardDto> selectPage(Map map) throws Exception;
    int update(BoardDto dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
}