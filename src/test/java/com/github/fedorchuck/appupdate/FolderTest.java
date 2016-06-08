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

import com.github.fedorchuck.appupdate.update.Folder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class FolderTest {

    @Test
    public void getSubDir() {
        List<String> expected = Arrays.asList(
                "build", "gradle", "src", ".gitignore", ".git", "LICENSE", "Copyright",
        "build.gradle", "README.md", "gradlew.bat", "log", ".gradle", "gradlew", "settings.gradle", ".idea");
        List<String> actual = Folder.getSubDir(new File("").getAbsolutePath());
        Assert.assertEquals(expected,actual);
    }

}