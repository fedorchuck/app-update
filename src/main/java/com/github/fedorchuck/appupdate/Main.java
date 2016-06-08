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

package com.github.fedorchuck.appupdate;

import com.github.fedorchuck.appupdate.process.destroy.IProcessDestroyer;
import com.github.fedorchuck.appupdate.settings.Config;
import com.github.fedorchuck.appupdate.process.Utils;
import com.github.fedorchuck.appupdate.process.destroy.impl.LinuxProcessDestroyer;
import com.github.fedorchuck.appupdate.process.destroy.impl.WindowsProcessDestroyer;
import com.github.fedorchuck.appupdate.log.Log;
import com.github.fedorchuck.appupdate.model.Response;
import com.github.fedorchuck.appupdate.network.Server;
import com.github.fedorchuck.appupdate.update.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.github.fedorchuck.appupdate.log.Level.*;

public class Main {
    private static Log log = new Log(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        Config configFile = new Config();
        Map<String,List<String>> config = null;

        switch (args[0]){
            case "help":
                System.out.println("here will be help or documentation url");
                System.exit(0);
                break;
            default:
                config = configFile.get(args[0]);
        }

        if (args.length>1) {
            String toKill = args[2];
            int delay = Integer.parseInt(config.get("com.github.fedorchuck.appupdate.delay:").get(0));

            Thread.sleep(delay);

            IProcessDestroyer destroy;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                destroy = new WindowsProcessDestroyer();
            } else {
                destroy = new LinuxProcessDestroyer();
            }
            if (Utils.isItString(toKill))
                destroy.killByIdList(destroy.getProcessIdentifierList(toKill));
            else
                destroy.killById(Integer.parseInt(toKill));
        }

        Server server = new Server();
        Response response = server.get(config.get("com.github.fedorchuck.appupdate.serverUrl:").get(0));
        if (server.validate(response))
            server.download(response.getUrlToUpdate());
        else
            log.write("Bad data url.", FATAL);

        Data data = new Data();
        data.doBackup();
        data.install();
        data.restoreBackup();


    }
}
