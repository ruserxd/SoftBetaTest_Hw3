package org.project.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ScannerBaseStatusTest {

    private ScannerBaseStatus scannerBaseStatus;

    @BeforeEach
    void setUp() {
        System.out.println("\n------------------------\n");
        scannerBaseStatus = new ScannerBaseStatus();
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
    }
}