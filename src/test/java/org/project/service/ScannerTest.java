package org.project.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ScannerBaseStatusTest {

    // 模擬輸入用的
    private ScannerBaseStatus scannerBaseStatus;

    // 負責儲存輸出
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.out.println("\n------------------------\n");
        scannerBaseStatus = new ScannerBaseStatus();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void output() {
        log.info(outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("測試輸入到滿壘的情況")
    void testNormalInput() {
        log.info("測試輸入到滿壘的情況");
        String input = "1\n2\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerBaseStatus.scanner = new java.util.Scanner(System.in);
        boolean[] result = scannerBaseStatus.getBaseStatus();

        assertFalse(result[0]);
        assertTrue(result[1]);
        assertTrue(result[2]);
        assertTrue(result[3]);

        assertTrue(outputStreamCaptor.toString().contains("目前已經為滿壘，避免佔用系統，將自動退出輸入器"));
    }

    @Test
    @DisplayName("測試輸入錯誤資訊的情況")
    void testInvalidInput() {
        log.info("測試輸入錯誤資訊的情況");
        String input = "5\n-1\n1\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerBaseStatus.scanner = new java.util.Scanner(System.in);
        boolean[] result = scannerBaseStatus.getBaseStatus();

        assertFalse(result[0]);
        assertTrue(result[1]);
        assertFalse(result[2]);
        assertFalse(result[3]);

        assertTrue(outputStreamCaptor.toString().contains("請輸入在範圍 (0 ~ 4) 內"));
    }

    @Test
    @DisplayName("測試重複輸入的情況")
    void testDuplicateInput() {
        log.info("測試重複輸入的情況");
        String input = "1\n1\n2\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerBaseStatus.scanner = new java.util.Scanner(System.in);
        boolean[] result = scannerBaseStatus.getBaseStatus();

        assertFalse(result[0]);
        assertTrue(result[1]);
        assertTrue(result[2]);
        assertFalse(result[3]);

        assertTrue(outputStreamCaptor.toString().contains("這項1已經有選擇過了喔"));
    }

    @Test
    @DisplayName("測試輸入本壘的情況")
    void testHomeBase() {
        log.info("測試輸入本壘的情況");
        String input = "0\n1\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerBaseStatus.scanner = new java.util.Scanner(System.in);
        boolean[] result = scannerBaseStatus.getBaseStatus();

        assertFalse(result[0]);
        assertTrue(result[1]);
        assertFalse(result[2]);
        assertFalse(result[3]);

        //log.info(outputStreamCaptor.toString());
        assertTrue(outputStreamCaptor.toString().contains("站在本壘的已經得分了喔!!"));
    }

    @Test
    @DisplayName("測試輸入正常測資的情況")
    void testOneAndTwoBase() {
        log.info("測試輸入正常測資的情況");
        String input = "1\n2\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scannerBaseStatus.scanner = new java.util.Scanner(System.in);
        boolean[] result = scannerBaseStatus.getBaseStatus();

        assertFalse(result[0]);
        assertTrue(result[1]);
        assertTrue(result[2]);
        assertFalse(result[3]);
    }
}