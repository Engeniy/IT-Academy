package ru.mail.krivonos.al.lesson.twelve.model.enums;

public enum Volume {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private String description;

    Volume(String description) {
        this.description = description;
    }

    public void printDescripion() {
        System.out.println("Volume level is: " + description);
    }
}
