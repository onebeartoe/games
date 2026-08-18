package net.onebeartoe.type.areli.pojos;

public class Round {
    private int misses;
    private int words;
    private long start = -1;
    private long end = -1;

    public Round() {
    }

    public Round(int words, int misses) {
        this.words = words;
        this.misses = misses;
    }

    public int getMisses() {
        return misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public double getHitRatio() {
        if (words <= 0) {
            return 0.0;
        }
        int hits = Math.max(0, words - misses);
        return (hits / (double) words) * 100.0;
    }

    public String getSummaryText() {
        return String.format("Words: %d \t   Misses: %d \t   Hit Ratio: %.1f%%", words, misses, getHitRatio());
    }

    @Override
    public String toString() {
        return getSummaryText();
    }
}
