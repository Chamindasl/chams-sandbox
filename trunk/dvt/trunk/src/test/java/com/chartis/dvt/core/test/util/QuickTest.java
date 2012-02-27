package com.chartis.dvt.core.test.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.chartis.dvt.core.dao.GoldDao;
import com.chartis.dvt.core.dao.impl.GoldDaoImpl;


public class QuickTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public final void test() throws InterruptedException{
        GoldDao goldDao;
        GoldDao goldDao2 = new GoldDaoImpl();
       String name = GoldDao.class.getName();
    String name2 = goldDao2.getClass().getName();
    Assert.assertEquals(name, name2);
    }
    
}
