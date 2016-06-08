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

import java.lang.reflect.Field;

public class VariablesTest {

    public static void main(String[] args) throws IllegalAccessException {
//        System.out.println("\033[0m BLACK");
//        System.out.println("\033[31m RED");
//        System.out.println("\033[32m GREEN");
//        System.out.println("\033[33m YELLOW");
//        System.out.println("\033[34m BLUE");
//        System.out.println("\033[35m MAGENTA");
//        System.out.println("\033[36m CYAN");
//        System.out.println("\033[37m WHITE");

        Variables variables = new Variables();
        Field[] fields = variables.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object name = field.getName();
            Object value = field.get(variables);
            System.out.println("\033[35m "+name+"\t \t \033[32m "+value);
        }
    }
}