package net.onebeartoe.type.areli.sounds;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class SoundManager {
    private AudioClip lineBeamSound;
    private AudioClip removeTargetSound;
    private AudioClip levelIntroSound;

    public SoundManager() {
        loadSounds();
    }

    private void loadSounds() {
        lineBeamSound = loadClip("/net/onebeartoe/type/areli/sounds/line-beam-b.wav");
        removeTargetSound = loadClip("/net/onebeartoe/type/areli/sounds/line-beam-remove.wav");
        levelIntroSound = loadClip("/net/onebeartoe/type/areli/sounds/audio.1280453989123.wav");
    }

    private AudioClip loadClip(String resourcePath) {
        try {
            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                return new AudioClip(url.toExternalForm());
            } else {
                System.err.println("Sound resource not found: " + resourcePath);
            }
        } catch (Exception e) {
            System.err.println("Could not load sound resource " + resourcePath + ": " + e.getMessage());
        }
        return null;
    }

    public void playIntro() {
        if (levelIntroSound != null) {
            try {
                levelIntroSound.play();
            } catch (Exception e) {
                System.err.println("Error playing intro sound: " + e.getMessage());
            }
        }
    }

    public void playLineBeam() {
        if (lineBeamSound != null) {
            try {
                lineBeamSound.play();
            } catch (Exception e) {
                System.err.println("Error playing beam sound: " + e.getMessage());
            }
        }
    }

    public void playRemoveTarget() {
        if (removeTargetSound != null) {
            try {
                removeTargetSound.play();
            } catch (Exception e) {
                System.err.println("Error playing remove target sound: " + e.getMessage());
            }
        }
    }
}
