package com.example.android.retrofittoppops.threading;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    private final int threadPriority;

    public PriorityThreadFactory(int threadPriority) {
        this.threadPriority = threadPriority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(threadPriority);
                } catch (Throwable t) {

                }
                r.run();
            }
        };

        return new Thread(wrapperRunnable);
    }
}
