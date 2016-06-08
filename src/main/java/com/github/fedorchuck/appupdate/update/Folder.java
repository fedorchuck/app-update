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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static com.github.fedorchuck.appupdate.log.Level.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Folder {
    private static Log log = new Log(Folder.class);

    /**
     * @return {@code list} with <b>JUST</b> names sub folders and files.
     * */
    public static List<String> getSubDir(String path){
        File file = new File(path);
        String[] directories =
                file.list((current, name) -> new File(current, name).isDirectory() || new File(current, name).isFile());
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
            log.write("Failed to delete : " + thrash,ERROR);
    }

    public static void replaceOrCreate(String path){
        log.write("Try to replace or create directory: "+path,INFO);

        File destDir = new File(path);
        if (!destDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            destDir.mkdir();
        } else {
            Folder.delete(path);
            //noinspection ResultOfMethodCallIgnored
            destDir.mkdir();
        }

        log.write("Directory created successful: "+path,INFO);
    }

    public static void move(String from, String to){
        log.write("Try to move data from:"+from,INFO);

        try {
            Files.move(new File(from).toPath(),new File(to).toPath(),REPLACE_EXISTING);
        } catch (IOException e) {
            log.write(e,"cannot move "+from+" to "+to,FATAL);
        }

        log.write("Moved: Successful.",INFO);
    }

    @Deprecated
    public static void main(String[] args){
        System.out.println(getSubDir(new File("").getAbsolutePath()));
    }
}
