package com.chartis.dvt.core.test.util;

import org.junit.Before;
import org.junit.Test;

public class QuickTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public final void test(){
        byte b = 0x20; 
        String example = "0x20";
        byte[] bys = null;
        System.out.println("Text : " + b);
        System.out.println("Text [Byte Format] : " + bys);
//        String s = new String(bys);
//        System.out.println("Text Decryted :'" + s +"'");
        
        int i = 32;
        System.out.println( Integer.toHexString(i));
    }
    
}
