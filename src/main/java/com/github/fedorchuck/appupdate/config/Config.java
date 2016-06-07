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

package com.github.fedorchuck.appupdate.config;

import com.github.fedorchuck.appupdate.log.Level;
import com.github.fedorchuck.appupdate.log.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private Log log = new Log(this.getClass());

    public Map<String,List<String>> get(String path) {
        log.write("start reading config: " + path, Level.INFO);

        List<String> sheet = null;
        try {
            sheet = readFile(path);
        } catch (FileNotFoundException e) {
            log.write(e,"config not foud.",Level.FATAL);
        } catch (IOException e) {
            e.printStackTrace();
            log.write(e,"problem with reading config file.",Level.FATAL);
        }

        Map<String,List<String>> res = new HashMap<>();
        List<String> value = new ArrayList<>();
        String key = null;
        int i = 0;
        //noinspection ConstantConditions
        for (String line : sheet){
            i++;
            switch(line){
                case "com.github.fedorchuck.appupdate.doNotDelete:":
                    if (!value.isEmpty()){
                        res.put(key,value);
                        key = line;
                        value = new ArrayList<>();
                    } else
                        key = line;
                    break;
                case "com.github.fedorchuck.appupdate.delay:":
                    if (!value.isEmpty()){
                        res.put(key,value);
                        key = line;
                        value = new ArrayList<>();
                    } else
                        key = line;
                    break;
                case "com.github.fedorchuck.appupdate.serverUrl:":
                    if (!value.isEmpty()){
                        res.put(key,value);
                        key = line;
                        value = new ArrayList<>();
                    } else
                        key = line;
                    break;
                default:
                    value.add(line);
                    break;
            }
            if (i == sheet.size())
                res.put(key,value);
        }

        return res;
    }

    @SuppressWarnings("DuplicateThrows")
    private List<String> readFile(String path) throws FileNotFoundException, IOException {
        if (path==null)
            log.write("config can't be null",Level.FATAL);

        //noinspection ConstantConditions
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<String> out = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().startsWith("#"))
                if (!line.equals(""))
                    out.add(line);
        }
        return out;
    }
}
