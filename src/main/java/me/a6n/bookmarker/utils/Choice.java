package me.a6n.bookmarker.utils;

public class Choice {
    private boolean result;

    public static Choice of(boolean result) {
        return new Choice(result);
    }

    Choice(boolean result) {
        this.result = result;
    }

    public Choice ifTrue(Runnable runnable) {
        if (result) {
            runnable.run();
        }
        return this;
    }

    public Choice ifFalse(Runnable runnable) {
        if (!result) {
            runnable.run();
        }
        return this;
    }
}
