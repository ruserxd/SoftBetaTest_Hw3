package org.project;

import org.project.service.BaseBallForceOut;
import org.project.service.ScannerBaseStatus;

public class Main {
    public static void main(String[] args) {
        ScannerBaseStatus scannerBaseStatus = new ScannerBaseStatus();
        boolean[] baseStatus = scannerBaseStatus.getBaseStatus();;
        boolean[] canForceOutBase = BaseBallForceOut.getCanForceOutBase(baseStatus);
        System.out.println();
        for (boolean can:canForceOutBase) {
            System.out.print(can + " ");
        }
    }
}
