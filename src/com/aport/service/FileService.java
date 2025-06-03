package com.aport.service;

import com.aport.file.strategy.FileStrategy;

public class FileService {
    private static FileService instance;
    private FileStrategy strategy;

    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService(null);
        }
        return instance;
    }
    public static FileService getInstance(FileStrategy strategy) {
        if (instance == null) {
            instance = new FileService(strategy);
        } else {
            instance.setStrategy(strategy);
        }
        return instance;
    }
    private FileService(FileStrategy strategy) {
        this.strategy = strategy;
    }

    public void save(String filePath) {
        strategy.save(filePath);
    }

    public boolean load(String filePath) {
        return strategy.load(filePath);
    }

    public void setStrategy(FileStrategy strategy) {
        this.strategy = strategy;
    }
}
