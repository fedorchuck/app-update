package com.github.fedorchuck.app_update;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {
    @Test   //arg help
    public void test01() throws IOException, InterruptedException {
        Main.main(new String[]{"help"});
    }

    @Test   //kill process
    public void test02() throws IOException, InterruptedException {
        Process process;
        String processName;

        IDestroy destroy;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            destroy = new com.github.fedorchuck.app_update.impl.windows.Destroy();
            processName = "calc";
            process = Runtime.getRuntime().exec(processName);
        }
        else {
            destroy = new com.github.fedorchuck.app_update.impl.linux.Destroy();
            processName = "gnome-calculator";//"slack";
            process = Runtime.getRuntime().exec(processName);
            processName = "gnome-calculato";//name MUST be less than 16 char!
        }

        Thread.sleep(5000);
        destroy.killByListId(destroy.getListProcessIdentifier(processName));
    }
}