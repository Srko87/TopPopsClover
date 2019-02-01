package com.example.android.retrofittoppops.thread;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    private final int threadPriority;

    PriorityThreadFactory(int threadPriority) {
        this.threadPriority = threadPriority;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Runnable wrapperRunnable = () -> {
            try {
                Process.setThreadPriority(threadPriority);
                runnable.run();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        return new Thread(wrapperRunnable);
    }
}
