package com.mosadie.advent2022.day7;

import java.util.HashMap;
import java.util.Map;

public class Directory extends File {

    private final Map<String, File> children;
    private final Directory parent;

    public Directory(String name, Directory parent) {
        super(0, name);
        children = new HashMap<>();
        this.parent = parent;
    }

    @Override
    public int getSize() {
        int size = 0;
        for(File child : children.values()) {
            size += child.getSize();
        }

        return size;
    }

    public void addChild(File child) {
        children.put(child.getName(), child);
    }

    public File getChild(String name) {
        if (children.containsKey(name)) {
            return children.get(name);
        }

        return null;
    }

    public Directory getParent() {
        return parent;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public Iterable<? extends File> getChildren() {
        return children.values();
    }
}
