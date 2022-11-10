package com.ciclabsindia.cic.model;

public class FileInfo {
    String filename, fileURL;

    public FileInfo(String filename, String fileURL) {
        this.filename = filename;
        this.fileURL = fileURL;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
