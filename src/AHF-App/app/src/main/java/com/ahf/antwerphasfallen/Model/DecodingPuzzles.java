package com.ahf.antwerphasfallen.Model;

import android.media.Image;

public class DecodingPuzzles extends Puzzles {
    Image decodingdiagram;
    Image key;
    String difficulty;

    public void setDecodingdiagram(Image decodingdiagram) {
        this.decodingdiagram = decodingdiagram;
    }

    public void setKey(Image key) {
        this.key = key;
    }

    public String getDifficulty(){
        return difficulty;
    }
}
