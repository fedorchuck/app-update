package com.github.fedorchuck.app_update.impl.linux;


import com.github.fedorchuck.app_update.IDestroy;
import com.github.fedorchuck.app_update.Utils;

import java.io.IOException;
import java.util.List;

public class Destroy implements IDestroy{

    @Override
    public void killByListId(List<Integer> pid) throws IOException {
        Runtime rt = Runtime.getRuntime();
        for (int id : pid)
            rt.exec("kill -9 " + id);
    }

    @Override
    public List<Integer> getListProcessIdentifier(String processNameToKill) throws IOException {
        Process process = Runtime.getRuntime().exec("ps -e");

        return Utils.read(process, processNameToKill);
    }
}
