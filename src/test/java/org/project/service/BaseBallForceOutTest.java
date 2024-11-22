package org.project.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BaseBallForceOutTest {
    // 用來存放預計輸入的資料
    boolean[] testBaseStatus = new boolean[4];

    // 用來存放預計結果的資料
    boolean[] expectForceOutStatus = new boolean[4];
    CountBaseBallForceOut countBaseBallForceOut = new CountBaseBallForceOut();

    @BeforeAll
    static void info() {
        log.info("針對1, 2, 3 壘有無人的情況做測試，共有 8 筆");
    }

    @BeforeEach
    void setTestBaseStatus() {
        log.info("對測試資料先進行初始化");
        for (int i = 0; i < 4; i++) {
            testBaseStatus[i] = FALSE;
            expectForceOutStatus[i] = FALSE;
        }
    }

    @Test
    @DisplayName("針對沒有人在壘包")
    void testNoOneInBase() {
        // 沒有人在壘包上
        testBaseStatus[1] = FALSE;
        testBaseStatus[2] = FALSE;
        testBaseStatus[3] = FALSE;

        // 只能封殺一壘
        expectForceOutStatus[1] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對一壘有人在壘包")
    void testOnlyFirstBase() {
        // 一壘有人
        testBaseStatus[1] = TRUE;
        testBaseStatus[2] = FALSE;
        testBaseStatus[3] = FALSE;

        // 可以封殺一、二壘
        expectForceOutStatus[1] = TRUE;
        expectForceOutStatus[2] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對二壘有人在壘包")
    void testOnlySecondBase() {
        // 二壘有人
        testBaseStatus[1] = FALSE;
        testBaseStatus[2] = TRUE;
        testBaseStatus[3] = FALSE;

        // 只能封殺一壘
        expectForceOutStatus[1] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對三壘有人在壘包")
    void testOnlyThirdBase() {
        // 三壘有人
        testBaseStatus[1] = FALSE;
        testBaseStatus[2] = FALSE;
        testBaseStatus[3] = TRUE;

        // 只能封殺一壘
        expectForceOutStatus[1] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對一、二壘有人在壘包")
    void testFirstAndSecondBase() {
        // 一、二壘有人
        testBaseStatus[1] = TRUE;
        testBaseStatus[2] = TRUE;
        testBaseStatus[3] = FALSE;

        // 可以封殺一、二、三壘
        expectForceOutStatus[1] = TRUE;
        expectForceOutStatus[2] = TRUE;
        expectForceOutStatus[3] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對一、三壘有人在壘包")
    void testFirstAndThirdBase() {
        // 一、三壘有人
        testBaseStatus[1] = TRUE;
        testBaseStatus[2] = FALSE;
        testBaseStatus[3] = TRUE;

        // 可以封殺一、二壘
        expectForceOutStatus[1] = TRUE;
        expectForceOutStatus[2] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對二、三壘有人在壘包")
    void testSecondAndThirdBase() {
        // 二、三壘有人
        testBaseStatus[1] = FALSE;
        testBaseStatus[2] = TRUE;
        testBaseStatus[3] = TRUE;

        // 只能封殺一壘
        expectForceOutStatus[1] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    @Test
    @DisplayName("針對一、二、三壘有人在壘包")
    void testAllBasesOccupied() {
        // 滿壘
        testBaseStatus[1] = TRUE;
        testBaseStatus[2] = TRUE;
        testBaseStatus[3] = TRUE;
        // 可以封殺所有壘包
        expectForceOutStatus[0] = TRUE;
        expectForceOutStatus[1] = TRUE;
        expectForceOutStatus[2] = TRUE;
        expectForceOutStatus[3] = TRUE;
        assertArrayEquals(expectForceOutStatus, countBaseBallForceOut.getCanForceOutBase(testBaseStatus));
    }

    // IDEA 會在測試時打開 -ea
    @Test
    @DisplayName("針對輸入資料有問題測試")
    void testBaseStatusNotFourLength() {
        boolean[] temporaryArray = new boolean[5];
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> countBaseBallForceOut.getCanForceOutBase(temporaryArray),
                "為正確拋出例外資訊"
        );

        assertEquals("壘包的狀態必須為 4 才行!!!", thrown.getMessage());
    }
}