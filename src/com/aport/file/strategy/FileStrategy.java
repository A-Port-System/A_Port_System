package com.aport.file.strategy;

public interface FileStrategy {
    void save(String filePath);
    boolean load(String filePath);
}
