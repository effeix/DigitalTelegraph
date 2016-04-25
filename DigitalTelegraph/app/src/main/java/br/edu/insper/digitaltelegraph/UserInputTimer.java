package br.edu.insper.digitaltelegraph;

public class UserInputTimer {

    private long start;

    public UserInputTimer() {
        start = 0;
    }

    public void start() {
        if(start == 0)
            start = System.currentTimeMillis();
    }

    // return time (in seconds) since this object was created
    public double elapsedTime() {
        if(start == 0) { return 0; } //You need to start it first

        double time = (System.currentTimeMillis() - start);
        start = 0; // reset start to allow you to start it again later
        return time;
    }
}
