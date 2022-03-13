package com.examples.popularmoviesapp.Utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static final int INSTANCE_NUM_THREAD = 4;
    private static AppExecutor INSTANCE;
    public static final ExecutorService executorService = Executors.newScheduledThreadPool(INSTANCE_NUM_THREAD);

    public static AppExecutor getINSTANCE() {
        return INSTANCE == null ? new AppExecutor() : INSTANCE;
    }


}
