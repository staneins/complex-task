package com.kaminsky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private CyclicBarrier barrier;
    private ExecutorService executor;
    private List<ComplexTask> tasks = new ArrayList<>();
    private List<Integer> results = Collections.synchronizedList(new ArrayList<>());

    public ComplexTaskExecutor(int num) {
        for (int i = 0; i < num; i++) {
            tasks.add(new ComplexTask());
        }
    }

    public void executeTasks(int numberOfTasks) {
        barrier = new CyclicBarrier(numberOfTasks);
        executor = Executors.newFixedThreadPool(numberOfTasks);
        for (ComplexTask task : tasks) {
            executor.submit(() -> {
                int result = task.execute();
                results.add(result);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
