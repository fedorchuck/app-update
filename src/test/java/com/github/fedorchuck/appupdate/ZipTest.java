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

public class ZipTest {

    public static void main(String[] args) {
        String zipFilePath = "/home/fedorchuck/Dropbox/font-awesome-4.6.2.zip";
        String destDirectory = "/home/fedorchuck/Desktop/tmp";
        Zip zip = new Zip();
        try {
            zip.extract(zipFilePath, destDirectory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}