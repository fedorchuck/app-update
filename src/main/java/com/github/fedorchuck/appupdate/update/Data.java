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

package com.github.fedorchuck.appupdate.update;

import com.github.fedorchuck.appupdate.log.Log;

import static com.github.fedorchuck.appupdate.Variables.*;
import static com.github.fedorchuck.appupdate.log.Level.*;

import java.util.List;

public class Data {
    private Log log = new Log(this.getClass());

    private List<String> pathFolders;

    public void doBackup(List<String> pathFolders) {
        log.write("Started do backup.", INFO);
        this.pathFolders = pathFolders;
        log.write("Destination directory: "+BACKUP_DIRECTORY, INFO);

        Folder.replaceOrCreate(BACKUP_DIRECTORY);

        for (String data : pathFolders){
            //TODO: bags
            Folder.move(data,BACKUP_DIRECTORY);
        }

        log.write("backup: OK", INFO);
    }

    public void cleanup() {

    }

    public void install(){
        log.write("Started installing data from:"+DOWNLOAD_DIRECTORY,INFO);
        Zip zip = new Zip();
        zip.extract(DOWNLOAD_DIRECTORY+DOWNLOAD_DIST,HOME_DIRECTORY);
    }

    public void restoreBackup() {

    }
}

