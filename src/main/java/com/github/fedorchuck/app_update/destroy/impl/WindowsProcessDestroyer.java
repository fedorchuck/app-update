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

package com.github.fedorchuck.app_update.destroy.impl;

import com.github.fedorchuck.app_update.destroy.IProcessDestroyer;
import com.github.fedorchuck.app_update.Utils;

import java.io.IOException;
import java.util.List;

public class WindowsProcessDestroyer implements IProcessDestroyer {

    @Override
    public void killByListId(List<Integer> pid) throws IOException {
        Runtime rt = Runtime.getRuntime();
        for (int id : pid)
            rt.exec("taskkill " + id);
    }

    @Override
    public List<Integer> getListProcessIdentifier(String processNameToKill) throws IOException {
        Process process = Runtime.getRuntime().exec
                (System.getenv("windir") +"\\system32\\"+"tasklist.exe");

        return Utils.read(process, processNameToKill);
    }
}
