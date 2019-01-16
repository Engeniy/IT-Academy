package ru.mail.krivonos.al.lesson.twelve.model;

import ru.mail.krivonos.al.lesson.twelve.model.enums.Volume;

public class MediaBox {

    private Volume volume;

    public MediaBox() {
        volume = Volume.MEDIUM;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}
