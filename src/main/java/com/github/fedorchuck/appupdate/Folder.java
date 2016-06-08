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

import com.github.fedorchuck.appupdate.log.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import static com.github.fedorchuck.appupdate.log.Level.*;

public class Folder {
    private static Log log = new Log(Folder.class);

    /**
     * @return {@code list} with <b>JUST</b> names sub folders.
     * */
    public static List<String> getSubDir(String path){
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
//        System.out.println(Arrays.toString(directories));
        return Arrays.asList(directories);
    }

    public static void delete(String path){
        File thrash = new File(path);
        delete(thrash);
    }

    public static void delete(File thrash){
        if (thrash.isDirectory())
            //noinspection ConstantConditions
            for (File subTrash : thrash.listFiles())
                delete(subTrash);
        else
            //noinspection ResultOfMethodCallIgnored
            thrash.delete();
        if (!thrash.delete())
            log.write("Failed to delete : " + thrash,FATAL);
    }

    @Deprecated
    public static void main(String[] args){
        System.out.println(getSubDir(new File("").getAbsolutePath()));
    }
}
