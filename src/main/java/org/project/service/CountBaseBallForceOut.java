package org.project.service;

import static java.lang.Boolean.TRUE;

public final class CountBaseBallForceOut {
    /**
     * boolean 0 代表壘包沒人, 1 代表壘包有人
     * base 0 -> 本壘, 1 -> 1壘, 2 -> 2壘, 3 -> 3壘
     */
    public boolean[] getCanForceOutBase(boolean[] baseBagStatus) {
        // 測試是否保持壘包狀態有 4 個
        assert baseBagStatus.length == 4:"壘包的狀態必須為 4 才行!!!";

        boolean[] base = new boolean[4];

        // 判斷打擊出去後哪些壘包是否可觸殺
        // 一壘一定可以觸殺
        // 如果一壘有人，則二壘也可以觸殺
        // 如果一、二壘有人，則三壘也可以觸殺
        // 如果一、二、三壘有人，則本壘也可以觸殺
        base[1] = TRUE;
        if (baseBagStatus[1] == TRUE) {
            base[2] = TRUE;
            if (baseBagStatus[2] == TRUE) {
                base[3] = TRUE;
                if (baseBagStatus[3] == TRUE) {
                    base[0] = TRUE;
                }
            }
        }

        return base;
    }
}
