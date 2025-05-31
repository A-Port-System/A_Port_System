package com.aport.strategy.file;

public interface FileStrategy {
    void save(String filePath);
    boolean load(String filePath);
}
