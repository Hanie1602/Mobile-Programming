package com.example.lab51;

public class Module {
    private int iconRes;
    private String title;
    private String description;
    private String category;

    public Module(int iconRes, String title, String description, String category) {
        this.iconRes     = iconRes;
        this.title       = title;
        this.description = description;
        this.category    = category;
    }

    public int getIconRes()       {
        return iconRes;
    }

    public void setIconRes(int iconRes){
        this.iconRes = iconRes;
    }

    public String getTitle()      {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getCategory()   {
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
