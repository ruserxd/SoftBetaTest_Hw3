package org.project;

import org.project.service.BaseBallForceOut;
import org.project.service.OutPutForceOutResult;
import org.project.service.ScannerBaseStatus;

public class Main {
    public static void main(String[] args) {
        ScannerBaseStatus scannerBaseStatus = new ScannerBaseStatus();
        boolean[] baseStatus = scannerBaseStatus.getBaseStatus();;
        boolean[] canForceOutBase = BaseBallForceOut.getCanForceOutBase(baseStatus);

        System.out.println("結果");
        OutPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);
    }
}