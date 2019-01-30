package com.example.android.retrofittoppops.threading;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;


public class DefaultExecutorSupplier {

    // number of cores for number of threads
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private final ThreadPoolExecutor forBackgroundTasks;

    private final ThreadPoolExecutor forLightWeightBackgroundTasks;

    private final Executor mainThreadExecutor;

    //instance of defaultExecutroSupplier
    private static DefaultExecutorSupplier instance;

    public static DefaultExecutorSupplier getInstance() {
        if (instance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                instance = new DefaultExecutorSupplier();
            }
        }
        return instance;
    }

    private DefaultExecutorSupplier() {

        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        forBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                backgroundPriorityThreadFactory
        );

        forLightWeightBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                backgroundPriorityThreadFactory
        );

        mainThreadExecutor = new MainThreadExecutor();
    }

    public ThreadPoolExecutor forBackgroundTasks() {
        return forBackgroundTasks;
    }

    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return forLightWeightBackgroundTasks;
    }

    public Executor forMainThreadTasks() {
        return mainThreadExecutor;
    }
}
