package org.project.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class OutPutForceOutResult {
    public static void outForceOutResult(boolean[] baseStatus, boolean[]  canForceOutBase) {
        boolean judgeInput = FALSE;
        for (int i = 0; i < 4; i++) {
            if (baseStatus[i]) {
                System.out.print(i + "B ");
                judgeInput = TRUE;
            }
        }
        if (!judgeInput)
            System.out.print("X");
        System.out.print(" -> ");
        for (int i = 0; i < 4; i++) {
            if (canForceOutBase[i]) {
                if (i == 0) {
                    System.out.print("HB ");
                } else {
                    System.out.print(i + "B ");
                }
            }
        }
    }
}
