package org.project;

import org.project.service.CountBaseBallForceOut;
import org.project.service.OutPutForceOutResult;
import org.project.service.ScannerBaseStatus;

public class Main {
    public static void main(String[] args) {
        ScannerBaseStatus scannerBaseStatus = new ScannerBaseStatus();
        boolean[] baseStatus = scannerBaseStatus.getBaseStatus();
        CountBaseBallForceOut countBaseBallForceOut = new CountBaseBallForceOut();
        boolean[] canForceOutBase = countBaseBallForceOut.getCanForceOutBase(baseStatus);
        OutPutForceOutResult outPutForceOutResult = new OutPutForceOutResult();
        outPutForceOutResult.outForceOutResult(baseStatus, canForceOutBase);
    }
}