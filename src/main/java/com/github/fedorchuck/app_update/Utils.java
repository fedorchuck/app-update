package com.github.fedorchuck.app_update;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Integer> read (Process process, String processNameToKill) throws IOException {
        BufferedReader input =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<Integer> pid = new ArrayList<>();
        String[] id;
        while ((line = input.readLine()) != null) {
            line = line.trim();
            if (line.endsWith(processNameToKill)){//name MUST be less than 16 char!
                id = line.split(" ");

                pid.add(Integer.parseInt(id[0]));

            }
            System.out.println(line);
        }

        input.close();
        return pid;
    }
}
