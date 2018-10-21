package ru.akolupaev.listviewfile;

import java.io.File;

public class State {

    private String name; // название
    private String capital;  // столица
    private int flagResource; // ресурс флага
    private File nameFile;
    private String fullPath; // название

    public State(String name, String capital, int flag, File nameFile, String fullPath){

        this.name=name;
        this.capital=capital;
        this.flagResource=flag;
        this.nameFile = nameFile;
        this.fullPath = fullPath;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getNameFile() {
        return this.nameFile;
    }

    public void setNameFile(File nameFile) {
        this.nameFile = nameFile;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}