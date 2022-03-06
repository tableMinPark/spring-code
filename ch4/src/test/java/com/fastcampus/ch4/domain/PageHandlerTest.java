package com.fastcampus.ch4.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void test() {
        PageHandler ph = new PageHandler(255, 25);
        ph.print();
        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getEndPage() == 26);
    }

    @Test
    public void test2() {
        PageHandler ph = new PageHandler(220, 20);
        ph.print();
        assertTrue(ph.isShowNext() == true);
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEndPage() == 20);
        assertTrue(ph.isShowNext() == true);
    }

    @Test
    public void test3() {
        PageHandler ph = new PageHandler(220, 10);
        ph.print();
        assertTrue(ph.isShowPrev() == false);
        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 10);
        assertTrue(ph.isShowNext() == true);
    }

    @Test
    public void test4() {
        PageHandler ph = new PageHandler(220, 21);
        ph.print();
        assertTrue(ph.isShowPrev() == true);
        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getEndPage() == 22);
        assertTrue(ph.isShowNext() == false);
    }
}