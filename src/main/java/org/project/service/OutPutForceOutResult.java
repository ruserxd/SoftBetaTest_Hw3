package org.project.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class OutPutForceOutResult {
    public void outForceOutResult(boolean[] baseStatus, boolean[] canForceOutBase) {
        // 判斷是否壘包有人的情況
        boolean judgeInput = FALSE;

        // 輸出輸入結果
        System.out.println("結果");
        for (int i = 0; i < 4; i++) {
            if (baseStatus[i]) {
                System.out.print(i + "B ");
                judgeInput = TRUE;
            }
        }

        // 無人要輸出 X
        if (!judgeInput)
            System.out.print("X");

        System.out.print(" -> ");

        // 輸出獲得結果
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
