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

import java.io.File;
import java.util.List;

public class Variables {
    public static List<String> LOG;
    public static final String HOME_DIRECTORY =
            new File(new File("").getAbsolutePath()).getParentFile().getAbsolutePath()+File.separator;
    public static final String HOME_DIRECTORY_APPUPDATER = HOME_DIRECTORY+"app-updater"+File.separator;
    public static final String DOWNLOAD_DIRECTORY = HOME_DIRECTORY+"tmp"+File.separator;
    public static String DOWNLOAD_DIST = "newDist.zip";
    public static final String BACKUP_DIRECTORY = HOME_DIRECTORY+"backup"+File.separator;
}
