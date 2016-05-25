/*
 * Copyright [2016] [Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fedorchuck.app_update;

import com.github.fedorchuck.app_update.impl.Execute;
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

//    @Test   //execute
    public void test03() throws IOException, InterruptedException {
        Execute run = new Execute();

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            run.command("src/test/java/com/github/fedorchuck/app_update/resources/test.bat");
        }
        else {
            run.command("src/test/java/com/github/fedorchuck/app_update/resources/test.sh");
        }
    }

}