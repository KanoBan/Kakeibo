package com.example;

import java.util.HashMap;
import java.util.Map;

public class SettingsManager {
    private Map<String, String> settings;

    public SettingsManager() {
        settings = new HashMap<>();
    }

    public void addSetting(String key, String value) {
        settings.put(key, value);
    }

    public String getSetting(String key) {
        return settings.getOrDefault(key, "Not Found");
    }

    public void updateSetting(String key, String value) {
        if (settings.containsKey(key)) {
            settings.put(key, value);
        }
    }

    public void removeSetting(String key) {
        settings.remove(key);
    }

    public Map<String, String> getAllSettings() {
        return new HashMap<>(settings);
    }
}
