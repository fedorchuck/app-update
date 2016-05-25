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

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //System.out.println("arg: " + Arrays.toString(args));

        String oldJar;//way to old jar        0
        String newJar;//way to new jar        1
        //process name to kill                2
        //kill after [seconds]                3
        switch (args[0]){
            case "help":
                System.out.println("here will be help");
                break;
            default:
                oldJar = args[0];
        }
        if (args.length>1) {
            String processNameToKill = args[2];//
            int killAfter = Integer.parseInt(args[3]);

            Thread.sleep(killAfter * 1000);

            IDestroy destroy;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                destroy = new com.github.fedorchuck.app_update.impl.windows.Destroy();
            } else {
                destroy = new com.github.fedorchuck.app_update.impl.linux.Destroy();
            }

            destroy.killByListId(destroy.getListProcessIdentifier(processNameToKill));
        }
    }
}
