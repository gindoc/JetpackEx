package com.a3xh1.jetpackex;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Author: GIndoc on 2019/4/27.
 * FOR   :
 */
@RunWith(JUnit4.class)
public class Test {

    @org.junit.Test
    public void test() {
        for (int i = 0; i < 4; ++i) {
            switch (i) {
                case 0:
                    System.out.print(i);
                    break;

                case 1:
                    System.out.print(i);
                    return;

                case 2:
                    System.out.print(i);
                    break;
            }
        }
    }
}
