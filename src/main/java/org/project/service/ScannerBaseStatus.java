package org.project.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
public class ScannerBaseStatus {
    private final boolean[] baseStatus = new boolean[4];
    public Scanner scanner = new Scanner(System.in);
    private int countTrue = 0;

    // 輸入獲得壘包的狀態
    public boolean[] getBaseStatus() {
        boolean keepScan = TRUE;
        while (keepScan) {

            // 針對已經滿壘的情況
            if (countTrue >= 3) {
                System.out.println("目前已經為滿壘，避免佔用系統，將自動退出輸入器");
                break;
            }

            System.out.println("請選擇目前哪些壘包有人 (0 ~ 3), e.x. 0 代表本壘, 1 代表 1壘...");
            System.out.println("輸入 4 可退出選擇器");
            int base = scanner.nextInt();

            // 針對已經選擇過的，不重複
            if (base >= 0 && base < 3 && baseStatus[base]) {
                System.out.println("這項" + base + "已經有選擇過了喔");
                continue;
            }

            // 針對輸入內容做相對應操作
            switch (base) {
                case 0:
                    System.out.println("站在本壘的已經得分了喔!!");
                    break;
                case 1:
                    baseStatus[1] = TRUE;
                    countTrue++;
                    break;
                case 2:
                    baseStatus[2] = TRUE;
                    countTrue++;
                    break;
                case 3:
                    baseStatus[3] = TRUE;
                    countTrue++;
                    break;
                case 4:
                    keepScan = FALSE;
                    break;
                default:
                    // 針對輸入的範圍
                    System.out.println("請輸入在範圍 (0 ~ 4) 內");
                    break;
            }
        }

        log.info("獲得了");
        log.info("[本壘  ,   1壘,   2壘,   3壘]\n{}", Arrays.toString(baseStatus));

        System.out.println("獲得了");
        for (int i = 0; i < 4; i++) {
            System.out.print(baseStatus[i] + " ");
        }
        System.out.println();
        scanner.close();

        return baseStatus;
    }
}
