package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (BufferedReader buffer = Files.newBufferedReader(Path.of(file.getAbsolutePath()))) {
            Map<String, String> map = new HashMap<>();
            buffer.lines().forEach(line -> {
                String[] keyValue = line.split(": ");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    map.put(key, value);
                }
            });
            profile.setName(map.get("Name"));
            profile.setAge(Integer.parseInt(map.get("Age")));
            profile.setEmail(map.get("Email"));
            profile.setPhone(Long.parseLong(map.get("Phone")));
        } catch (IOException e) {
            return null;
        }
        return profile;
    }
}
