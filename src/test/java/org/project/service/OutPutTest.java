package org.project.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class OutPutForceOutResultTest {

    private OutPutForceOutResult outPutForceOutResult;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.out.println("\n------------------------\n");
        outPutForceOutResult = new OutPutForceOutResult();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // 移除所有空白和換行
    // \\s 代表可多種 \t \n \r space...
    private String removeAllWhitespace(String str) {
        return str.replaceAll("\\s+", "");
    }

    @Test
    @DisplayName("測試沒有人在壘包的情況")
    void testNoBaseRunner() {
        log.info("測試沒有人在壘包的情況");

        boolean[] baseStatus = {false, false, false, false};
        boolean[] canForceOutBase = {false, false, false, false};

        outPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);

        assertEquals("結果X->", removeAllWhitespace(outputStreamCaptor.toString()));
    }

    @Test
    @DisplayName("測試一壘有人的情況")
    void testRunnerOnFirstBase() {
        log.info("測試一壘有人的情況");

        boolean[] baseStatus = {false, true, false, false};
        boolean[] canForceOutBase = {false, false, true, false};

        outPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);

        assertEquals("結果1B->2B", removeAllWhitespace(outputStreamCaptor.toString()));
    }

    @Test
    @DisplayName("測試滿壘的情況")
    void testBasesLoaded() {
        log.info("測試滿壘的情況");

        boolean[] baseStatus = {false, true, true, true};
        boolean[] canForceOutBase = {true, true, true, true};

        outPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);

        assertEquals("結果1B2B3B->HB1B2B3B", removeAllWhitespace(outputStreamCaptor.toString()));
    }

    @Test
    @DisplayName("測試一三壘有人的情況")
    void testRunnersOnFirstAndThirdBase() {
        log.info("測試一三壘有人的情況");

        boolean[] baseStatus = {false, true, false, true};
        boolean[] canForceOutBase = {true, false, true, false};

        outPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);

        assertEquals("結果1B3B->HB2B", removeAllWhitespace(outputStreamCaptor.toString()));
    }

    @Test
    @DisplayName("測試只有得分的情況")
    void testOnlyHomeBase() {
        log.info("測試只有得分的情況");

        boolean[] baseStatus = {false, false, false, false};
        boolean[] canForceOutBase = {true, false, false, false};

        outPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);

        assertEquals("結果X->HB", removeAllWhitespace(outputStreamCaptor.toString()));
    }
}