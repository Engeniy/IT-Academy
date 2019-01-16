package ru.mail.krivonos.al.lesson.twelve.impl;

import ru.mail.krivonos.al.lesson.twelve.MediaBoxService;
import ru.mail.krivonos.al.lesson.twelve.model.MediaBox;
import ru.mail.krivonos.al.lesson.twelve.model.enums.Volume;

public class MediaBoxServiceImpl implements MediaBoxService {

    @Override
    public void increaseVolume(MediaBox mediaBox) {
        Volume volume = mediaBox.getVolume();
        switch (volume) {
            case LOW:
                volume = Volume.MEDIUM;
                mediaBox.setVolume(volume);
                volume.printDescripion();
                break;
            case MEDIUM:
                volume = Volume.HIGH;
                mediaBox.setVolume(volume);
                volume.printDescripion();
                break;
            case HIGH:
                System.out.println("Volume is at max level!");
                break;
        }
    }

    @Override
    public void decreaseVolume(MediaBox mediaBox) {
        Volume volume = mediaBox.getVolume();
        switch (volume) {
            case LOW:
                System.out.println("Volume is at min level!");
                break;
            case MEDIUM:
                volume = Volume.LOW;
                mediaBox.setVolume(volume);
                volume.printDescripion();
                break;
            case HIGH:
                volume = Volume.MEDIUM;
                mediaBox.setVolume(volume);
                volume.printDescripion();
                break;

        }
    }
}
