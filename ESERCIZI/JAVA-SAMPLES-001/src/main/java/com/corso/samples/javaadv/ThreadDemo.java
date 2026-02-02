package com.corso.samples.javaadv;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Esempio completo e avanzato sull'uso dei Thread in Java
 * 
 * COS'È UN THREAD?
 * Un thread è un'unità di esecuzione indipendente all'interno di un processo.
 * Permette l'esecuzione concorrente di più task.
 * 
 * VANTAGGI:
 * - Migliore utilizzo CPU (multi-core)
 * - Responsività applicazioni (UI non bloccata)
 * - Operazioni asincrone (I/O, network)
 * 
 * SFIDE:
 * - Race conditions
 * - Deadlock
 * - Memory visibility
 * - Complexity
 * 
 * Include:
 * - Creazione thread (Thread, Runnable, Lambda)
 * - Thread lifecycle e stati
 * - Sincronizzazione (synchronized, Lock)
 * - Wait/Notify pattern
 * - Thread pools (ExecutorService)
 * - Callable e Future
 * - CompletableFuture (async programming)
 * - Thread-safe collections
 * - Volatile e Atomic variables
 * - ThreadLocal
 * - Deadlock detection e prevention
 * - Thread interruption
 * - Fork/Join framework
 * - Best practices
 */
public class ThreadDemo {

    public static void sample() throws Exception {
        ThreadDemo demo = new ThreadDemo();
        
        System.out.println("=== THREAD IN JAVA - GUIDA COMPLETA ===\n");
        
        // Basic Thread
        demo.cosaSonoIThread();
        demo.basicThreadCreation();
        demo.runnableInterface();
        demo.lambdaThreads();
        demo.threadLifecycle();
        
        // Synchronization
        demo.racConditionDemo();
        demo.synchronizedMethodDemo();
        demo.synchronizedBlockDemo();
        demo.reentrantLockDemo();
        demo.readWriteLockDemo();
        
        // Thread Communication
        demo.waitNotifyDemo();
        demo.producerConsumerDemo();
        
        // Thread Pools
        demo.executorServiceDemo();
        demo.fixedThreadPoolDemo();
        demo.cachedThreadPoolDemo();
        demo.scheduledExecutorDemo();
        
        // Callable and Future
        demo.callableDemo();
        demo.futureDemo();
        demo.completableFutureBasic();
        demo.completableFutureChaining();
        demo.completableFutureCombining();
        
        // Thread Safety
        demo.threadSafeCollections();
        demo.volatileDemo();
        demo.atomicVariablesDemo();
        demo.threadLocalDemo();
        
        // Advanced
        demo.deadlockDemo();
        demo.threadInterruption();
        demo.forkJoinDemo();
        
        // Best Practices
        demo.performanceComparison();
        demo.bestPractices();
        
        System.out.println("\n=== DEMO COMPLETATA ===");
    }

    // ==================== COS'È UN THREAD ====================

    public void cosaSonoIThread() {
        System.out.println("=== COS'È UN THREAD? ===\n");

        System.out.println("DEFINIZIONE:");
        System.out.println("Un thread è un'unità di esecuzione leggera all'interno di un processo.\n");

        System.out.println("PROCESSO vs THREAD:");
        System.out.println("PROCESSO:");
        System.out.println("  - Spazio di memoria isolato");
        System.out.println("  - Risorse separate");
        System.out.println("  - Creazione costosa");
        System.out.println("\nTHREAD:");
        System.out.println("  - Condivide memoria del processo");
        System.out.println("  - Risorse condivise");
        System.out.println("  - Creazione leggera\n");

        System.out.println("STATI DEL THREAD:");
        System.out.println("1. NEW - thread creato ma non avviato");
        System.out.println("2. RUNNABLE - in esecuzione o pronto");
        System.out.println("3. BLOCKED - in attesa di lock");
        System.out.println("4. WAITING - in attesa indefinita");
        System.out.println("5. TIMED_WAITING - in attesa temporizzata");
        System.out.println("6. TERMINATED - esecuzione completata\n");

        System.out.println("THREAD CORRENTE:");
        Thread current = Thread.currentThread();
        System.out.println("  Nome: " + current.getName());
        System.out.println("  ID: " + current.getId());
        System.out.println("  Priorità: " + current.getPriority());
        System.out.println("  Stato: " + current.getState());
        System.out.println("  Thread Group: " + current.getThreadGroup().getName());

        System.out.println("\n");
    }

    // ==================== BASIC THREAD CREATION ====================

    public void basicThreadCreation() throws InterruptedException {
        System.out.println("=== BASIC THREAD CREATION ===");

        // Metodo 1: Estendere Thread class
        class MyThread extends Thread {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(getName() + " - Count: " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        MyThread thread1 = new MyThread();
        thread1.setName("Thread-A");
        thread1.start(); // Avvia il thread

        thread1.join(); // Attende completamento

        System.out.println("\nThread completato!\n");
    }

    // ==================== RUNNABLE INTERFACE ====================

    public void runnableInterface() throws InterruptedException {
        System.out.println("=== RUNNABLE INTERFACE ===");

        // Metodo 2: Implementare Runnable (preferito)
        class MyTask implements Runnable {
            private String name;

            public MyTask(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println(name + " - Iteration: " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        Thread thread1 = new Thread(new MyTask("Task-1"));
        Thread thread2 = new Thread(new MyTask("Task-2"));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Tutti i task completati!\n");
    }

    // ==================== LAMBDA THREADS ====================

    public void lambdaThreads() throws InterruptedException {
        System.out.println("=== LAMBDA THREADS ===");

        // Metodo 3: Lambda expression (Java 8+)
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Lambda-Thread-1 - " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            IntStream.range(1, 4).forEach(i -> {
                System.out.println("Lambda-Thread-2 - " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println();
    }

    // ==================== THREAD LIFECYCLE ====================

    public void threadLifecycle() throws InterruptedException {
        System.out.println("=== THREAD LIFECYCLE ===");

        Thread thread = new Thread(() -> {
            System.out.println("Thread running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread completing...");
        });

        System.out.println("1. Stato NEW: " + thread.getState());

        thread.start();
        System.out.println("2. Dopo start(): " + thread.getState());

        Thread.sleep(100);
        System.out.println("3. Durante esecuzione: " + thread.getState());

        thread.join();
        System.out.println("4. Stato TERMINATED: " + thread.getState());

        System.out.println();
    }

    // ==================== RACE CONDITION ====================

    public void racConditionDemo() throws InterruptedException {
        System.out.println("=== RACE CONDITION DEMO ===");

        class UnsafeCounter {
            private int count = 0;

            public void increment() {
                count++; // Non thread-safe!
            }

            public int getCount() {
                return count;
            }
        }

        UnsafeCounter counter = new UnsafeCounter();

        // 10 thread che incrementano 1000 volte
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Risultato atteso: 10000");
        System.out.println("Risultato ottenuto: " + counter.getCount());
        System.out.println("Race condition rilevata: " + (counter.getCount() != 10000));
        System.out.println();
    }

    // ==================== SYNCHRONIZED METHOD ====================

    public void synchronizedMethodDemo() throws InterruptedException {
        System.out.println("=== SYNCHRONIZED METHOD ===");

        class SafeCounter {
            private int count = 0;

            public synchronized void increment() {
                count++; // Thread-safe!
            }

            public synchronized int getCount() {
                return count;
            }
        }

        SafeCounter counter = new SafeCounter();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Risultato atteso: 10000");
        System.out.println("Risultato ottenuto: " + counter.getCount());
        System.out.println("Thread-safe: " + (counter.getCount() == 10000));
        System.out.println();
    }

    // ==================== SYNCHRONIZED BLOCK ====================

    public void synchronizedBlockDemo() throws InterruptedException {
        System.out.println("=== SYNCHRONIZED BLOCK ===");

        class BankAccount {
            private double balance = 1000.0;
            private final Object lock = new Object();

            public void withdraw(double amount) {
                synchronized (lock) {
                    if (balance >= amount) {
                        System.out.println(Thread.currentThread().getName() + 
                            " - Prelievo: " + amount);
                        balance -= amount;
                        System.out.println("  Saldo rimanente: " + balance);
                    } else {
                        System.out.println(Thread.currentThread().getName() + 
                            " - Saldo insufficiente");
                    }
                }
            }

            public double getBalance() {
                synchronized (lock) {
                    return balance;
                }
            }
        }

        BankAccount account = new BankAccount();

        Thread t1 = new Thread(() -> account.withdraw(600), "Thread-1");
        Thread t2 = new Thread(() -> account.withdraw(500), "Thread-2");
        Thread t3 = new Thread(() -> account.withdraw(300), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Saldo finale: " + account.getBalance());
        System.out.println();
    }

    // ==================== REENTRANT LOCK ====================

    public void reentrantLockDemo() throws InterruptedException {
        System.out.println("=== REENTRANT LOCK ===");

        class Resource {
            private final Lock lock = new ReentrantLock();
            private int value = 0;

            public void increment() {
                lock.lock();
                try {
                    value++;
                    System.out.println(Thread.currentThread().getName() + 
                        " - Valore: " + value);
                } finally {
                    lock.unlock(); // Sempre in finally!
                }
            }

            public boolean tryIncrement() {
                if (lock.tryLock()) {
                    try {
                        value++;
                        return true;
                    } finally {
                        lock.unlock();
                    }
                }
                return false;
            }

            public int getValue() {
                lock.lock();
                try {
                    return value;
                } finally {
                    lock.unlock();
                }
            }
        }

        Resource resource = new Resource();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                resource.increment();
            }, "Thread-" + (i + 1));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Valore finale: " + resource.getValue());
        System.out.println();
    }

    // ==================== READ WRITE LOCK ====================

    public void readWriteLockDemo() throws InterruptedException {
        System.out.println("=== READ WRITE LOCK ===");

        class SharedData {
            private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
            private final Lock readLock = rwLock.readLock();
            private final Lock writeLock = rwLock.writeLock();
            private int value = 0;

            public int read() {
                readLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + 
                        " - Lettura: " + value);
                    return value;
                } finally {
                    readLock.unlock();
                }
            }

            public void write(int newValue) {
                writeLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + 
                        " - Scrittura: " + newValue);
                    value = newValue;
                    Thread.sleep(100); // Simula operazione lenta
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    writeLock.unlock();
                }
            }
        }

        SharedData data = new SharedData();

        // Più reader simultanei
        Thread reader1 = new Thread(() -> data.read(), "Reader-1");
        Thread reader2 = new Thread(() -> data.read(), "Reader-2");
        Thread reader3 = new Thread(() -> data.read(), "Reader-3");

        // Un writer
        Thread writer = new Thread(() -> data.write(42), "Writer");

        reader1.start();
        reader2.start();
        writer.start();
        reader3.start();

        reader1.join();
        reader2.join();
        reader3.join();
        writer.join();

        System.out.println();
    }

    // ==================== WAIT/NOTIFY ====================

    public void waitNotifyDemo() throws InterruptedException {
        System.out.println("=== WAIT/NOTIFY PATTERN ===");

        class Message {
            private String content;
            private boolean available = false;

            public synchronized void send(String msg) {
                while (available) {
                    try {
                        wait(); // Attende che il messaggio sia consumato
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                content = msg;
                available = true;
                System.out.println("Inviato: " + msg);
                notify(); // Notifica il consumer
            }

            public synchronized String receive() {
                while (!available) {
                    try {
                        wait(); // Attende che il messaggio sia disponibile
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                available = false;
                notify(); // Notifica il producer
                System.out.println("Ricevuto: " + content);
                return content;
            }
        }

        Message message = new Message();

        Thread producer = new Thread(() -> {
            String[] messages = {"Messaggio 1", "Messaggio 2", "Messaggio 3"};
            for (String msg : messages) {
                message.send(msg);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                message.receive();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println();
    }

    // ==================== PRODUCER CONSUMER ====================

    public void producerConsumerDemo() throws InterruptedException {
        System.out.println("=== PRODUCER-CONSUMER PATTERN ===");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println("Prodotto: " + i + " (Queue size: " + queue.size() + ")");
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer item = queue.take();
                    System.out.println("  Consumato: " + item);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println();
    }

    // ==================== EXECUTOR SERVICE ====================

    public void executorServiceDemo() throws InterruptedException {
        System.out.println("=== EXECUTOR SERVICE ===");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " - Thread: " + 
                    Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown(); // Non accetta nuovi task
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Tutti i task completati!\n");
    }

    // ==================== FIXED THREAD POOL ====================

    public void fixedThreadPoolDemo() throws InterruptedException {
        System.out.println("=== FIXED THREAD POOL ===");

        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            Future<?> future = executor.submit(() -> {
                System.out.println("Processing task " + taskId + " on " + 
                    Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return taskId * 2;
            });
            futures.add(future);
        }

        System.out.println("Task inviati: " + futures.size());

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Thread pool terminato\n");
    }

    // ==================== CACHED THREAD POOL ====================

    public void cachedThreadPoolDemo() throws InterruptedException {
        System.out.println("=== CACHED THREAD POOL ===");

        ExecutorService executor = Executors.newCachedThreadPool();

        // Burst di task
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Quick task " + taskId);
            });
        }

        Thread.sleep(100);

        // Altri task dopo un delay
        for (int i = 6; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Delayed task " + taskId);
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println();
    }

    // ==================== SCHEDULED EXECUTOR ====================

    public void scheduledExecutorDemo() throws InterruptedException {
        System.out.println("=== SCHEDULED EXECUTOR ===");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Task singolo con delay
        System.out.println("Schedulato task con delay di 1 secondo...");
        scheduler.schedule(() -> {
            System.out.println("Task eseguito dopo delay!");
        }, 1, TimeUnit.SECONDS);

        // Task ripetuto a intervalli fissi
        System.out.println("Task ripetuto ogni 500ms (primi 3)...");
        AtomicInteger counter = new AtomicInteger(0);
        ScheduledFuture<?> repeatingTask = scheduler.scheduleAtFixedRate(() -> {
            int count = counter.incrementAndGet();
            System.out.println("  Ripetizione #" + count);
            if (count >= 3) {
                // Auto-cancellazione dopo 3 esecuzioni
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        Thread.sleep(2000);
        repeatingTask.cancel(false);

        scheduler.shutdown();
        scheduler.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println();
    }

    // ==================== CALLABLE DEMO ====================

    public void callableDemo() throws Exception {
        System.out.println("=== CALLABLE INTERFACE ===");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Callable ritorna un valore (a differenza di Runnable)
        Callable<Integer> task1 = () -> {
            Thread.sleep(100);
            return 42;
        };

        Callable<String> task2 = () -> {
            Thread.sleep(150);
            return "Hello from Callable";
        };

        Callable<Double> task3 = () -> {
            Thread.sleep(50);
            return Math.PI;
        };

        Future<Integer> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        Future<Double> future3 = executor.submit(task3);

        System.out.println("Task inviati, in attesa risultati...");

        // Bloccante fino al completamento
        System.out.println("Risultato 1: " + future1.get());
        System.out.println("Risultato 2: " + future2.get());
        System.out.println("Risultato 3: " + future3.get());

        executor.shutdown();
        System.out.println();
    }

    // ==================== FUTURE DEMO ====================

    public void futureDemo() throws Exception {
        System.out.println("=== FUTURE OPERATIONS ===");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(() -> {
            Thread.sleep(1000);
            return "Risultato dopo 1 secondo";
        });

        System.out.println("Task inviato");
        System.out.println("Is done: " + future.isDone());
        System.out.println("Is cancelled: " + future.isCancelled());

        // Timeout
        try {
            String result = future.get(500, TimeUnit.MILLISECONDS);
            System.out.println("Risultato: " + result);
        } catch (TimeoutException e) {
            System.out.println("Timeout! Task ancora in esecuzione");
        }

        // Attende completamento
        String finalResult = future.get();
        System.out.println("Risultato finale: " + finalResult);
        System.out.println("Is done: " + future.isDone());

        executor.shutdown();
        System.out.println();
    }

    // ==================== COMPLETABLE FUTURE BASIC ====================

    public void completableFutureBasic() throws Exception {
        System.out.println("=== COMPLETABLE FUTURE - BASIC ===");

        // Async computation
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Computing in: " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Hello";
        });

        String result = future1.get();
        System.out.println("Risultato: " + result);

        // Con callback
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            return "World";
        }).thenAccept(s -> {
            System.out.println("Callback ricevuto: " + s);
        });

        future2.join();

        // Completamento manuale
        CompletableFuture<String> future3 = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(200);
                future3.complete("Completato manualmente!");
            } catch (InterruptedException e) {
                future3.completeExceptionally(e);
            }
        }).start();

        System.out.println(future3.get());
        System.out.println();
    }

    // ==================== COMPLETABLE FUTURE CHAINING ====================

    public void completableFutureChaining() throws Exception {
        System.out.println("=== COMPLETABLE FUTURE - CHAINING ===");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("1. Fetch user data");
            return "User123";
        }).thenApply(userId -> {
            System.out.println("2. Fetch user details for: " + userId);
            return userId + "-Details";
        }).thenApply(details -> {
            System.out.println("3. Format result: " + details);
            return "Formatted: " + details;
        }).exceptionally(ex -> {
            System.out.println("Errore: " + ex.getMessage());
            return "Default value";
        });

        String result = future.get();
        System.out.println("Risultato finale: " + result);
        System.out.println();
    }

    // ==================== COMPLETABLE FUTURE COMBINING ====================

    public void completableFutureCombining() throws Exception {
        System.out.println("=== COMPLETABLE FUTURE - COMBINING ===");

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            System.out.println("Future 1 completed");
            return 10;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(150);
            System.out.println("Future 2 completed");
            return 20;
        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            sleep(50);
            System.out.println("Future 3 completed");
            return 30;
        });

        // Combina due future
        CompletableFuture<Integer> combined = future1.thenCombine(future2, (a, b) -> {
            System.out.println("Combining: " + a + " + " + b);
            return a + b;
        });

        System.out.println("Somma future1 + future2: " + combined.get());

        // Attende tutte
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);
        allOf.join();
        
        System.out.println("Tutte le future completate!");
        System.out.println("Somma totale: " + (future1.get() + future2.get() + future3.get()));

        System.out.println();
    }

    // ==================== THREAD SAFE COLLECTIONS ====================

    public void threadSafeCollections() throws InterruptedException {
        System.out.println("=== THREAD-SAFE COLLECTIONS ===");

        // CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();
        
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                list.add("Item-" + i);
                System.out.println("Added: Item-" + i);
                sleep(50);
            }
        });

        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("  Reading: " + list);
                sleep(60);
            }
        });

        writer.start();
        reader.start();
        writer.join();
        reader.join();

        // ConcurrentHashMap
        System.out.println("\n--- ConcurrentHashMap ---");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int value = i;
            Thread thread = new Thread(() -> {
                map.put("Key-" + value, value);
                System.out.println("Put: Key-" + value);
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Map finale: " + map);
        System.out.println();
    }

    // ==================== VOLATILE ====================

    public void volatileDemo() throws InterruptedException {
        System.out.println("=== VOLATILE KEYWORD ===");

        class VolatileExample {
            private volatile boolean running = true;
            private int counter = 0;

            public void stop() {
                running = false;
            }

            public void run() {
                while (running) {
                    counter++;
                }
                System.out.println("Thread terminato. Counter: " + counter);
            }
        }

        VolatileExample example = new VolatileExample();

        Thread worker = new Thread(example::run);
        worker.start();

        Thread.sleep(100);
        System.out.println("Stopping worker...");
        example.stop();

        worker.join(1000);
        System.out.println("Worker terminato: " + !worker.isAlive());
        System.out.println();
    }

    // ==================== ATOMIC VARIABLES ====================

    public void atomicVariablesDemo() throws InterruptedException {
        System.out.println("=== ATOMIC VARIABLES ===");

        AtomicInteger atomicCounter = new AtomicInteger(0);
        AtomicLong atomicLong = new AtomicLong(0);
        AtomicBoolean atomicFlag = new AtomicBoolean(false);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.incrementAndGet();
                    atomicLong.addAndGet(1);
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("AtomicInteger: " + atomicCounter.get());
        System.out.println("AtomicLong: " + atomicLong.get());

        // Compare and Set
        atomicFlag.set(false);
        boolean success = atomicFlag.compareAndSet(false, true);
        System.out.println("CAS success: " + success + ", value: " + atomicFlag.get());

        // Get and Update
        int previous = atomicCounter.getAndUpdate(n -> n * 2);
        System.out.println("Previous: " + previous + ", New: " + atomicCounter.get());

        System.out.println();
    }

    // ==================== THREAD LOCAL ====================

    public void threadLocalDemo() throws InterruptedException {
        System.out.println("=== THREAD LOCAL ===");

        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> 
            "Default-" + Thread.currentThread().getName()
        );

        class Task implements Runnable {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + " - Initial: " + value);
                
                threadLocal.set("Modified-" + Thread.currentThread().getName());
                
                System.out.println(Thread.currentThread().getName() + " - Modified: " + 
                    threadLocal.get());
            }
        }

        Thread t1 = new Thread(new Task(), "Thread-1");
        Thread t2 = new Thread(new Task(), "Thread-2");
        Thread t3 = new Thread(new Task(), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Main thread value: " + threadLocal.get());
        System.out.println();
    }

    // ==================== DEADLOCK ====================

    public void deadlockDemo() {
        System.out.println("=== DEADLOCK DEMO ===");

        final Object resource1 = new Object();
        final Object resource2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread-1: Locked resource 1");
                sleep(50);
                System.out.println("Thread-1: Waiting for resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread-1: Locked resource 2");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread-2: Locked resource 2");
                sleep(50);
                System.out.println("Thread-2: Waiting for resource 1...");
                synchronized (resource1) {
                    System.out.println("Thread-2: Locked resource 1");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join(1000);
            t2.join(1000);

            if (t1.isAlive() || t2.isAlive()) {
                System.out.println("\n⚠️  DEADLOCK RILEVATO!");
                System.out.println("Thread-1 alive: " + t1.isAlive() + ", state: " + t1.getState());
                System.out.println("Thread-2 alive: " + t2.isAlive() + ", state: " + t2.getState());
                
                // Force termination (non usare in produzione!)
                t1.interrupt();
                t2.interrupt();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSOLUZIONE: Lock ordering - sempre acquisire lock nello stesso ordine");
        System.out.println();
    }

    // ==================== THREAD INTERRUPTION ====================

    public void threadInterruption() throws InterruptedException {
        System.out.println("=== THREAD INTERRUPTION ===");

        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Working...");
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("Worker interrupted during sleep!");
                Thread.currentThread().interrupt(); // Ripristina flag
            }
            System.out.println("Worker terminated gracefully");
        });

        worker.start();
        Thread.sleep(500);
        
        System.out.println("Main: Interrupting worker");
        worker.interrupt();
        
        worker.join();
        System.out.println();
    }

    // ==================== FORK JOIN ====================

    public void forkJoinDemo() {
        System.out.println("=== FORK/JOIN FRAMEWORK ===");

        class SumTask extends RecursiveTask<Long> {
            private static final int THRESHOLD = 1000;
            private final long[] array;
            private final int start;
            private final int end;

            public SumTask(long[] array, int start, int end) {
                this.array = array;
                this.start = start;
                this.end = end;
            }

            @Override
            protected Long compute() {
                if (end - start <= THRESHOLD) {
                    // Calcolo diretto
                    long sum = 0;
                    for (int i = start; i < end; i++) {
                        sum += array[i];
                    }
                    return sum;
                } else {
                    // Divide
                    int mid = (start + end) / 2;
                    SumTask leftTask = new SumTask(array, start, mid);
                    SumTask rightTask = new SumTask(array, mid, end);
                    
                    leftTask.fork(); // Esegui async
                    long rightResult = rightTask.compute();
                    long leftResult = leftTask.join();
                    
                    return leftResult + rightResult;
                }
            }
        }

        long[] array = LongStream.range(1, 10001).toArray();
        
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);
        
        long start = System.nanoTime();
        long result = pool.invoke(task);
        long elapsed = System.nanoTime() - start;
        
        System.out.println("Somma array (10000 elementi): " + result);
        System.out.println("Tempo: " + (elapsed / 1_000_000) + " ms");
        System.out.println("Parallelismo: " + pool.getParallelism());
        
        pool.shutdown();
        System.out.println();
    }

    // ==================== PERFORMANCE COMPARISON ====================

    public void performanceComparison() throws InterruptedException {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        int iterations = 1_000_000;

        // Single thread
        long start = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < iterations; i++) {
            sum += i;
        }
        long singleThreadTime = System.nanoTime() - start;

        // Multi-thread (4 threads)
        start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Long>> futures = new ArrayList<>();
        
        int chunk = iterations / 4;
        for (int i = 0; i < 4; i++) {
            final int threadStart = i * chunk;
            final int threadEnd = (i == 3) ? iterations : (i + 1) * chunk;
            
            Future<Long> future = executor.submit(() -> {
                long partialSum = 0;
                for (int j = threadStart; j < threadEnd; j++) {
                    partialSum += j;
                }
                return partialSum;
            });
            futures.add(future);
        }

        long multiSum = 0;
        for (Future<Long> future : futures) {
            try {
                multiSum += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long multiThreadTime = System.nanoTime() - start;

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // Parallel Stream
        start = System.nanoTime();
        long streamSum = LongStream.range(0, iterations)
            .parallel()
            .sum();
        long parallelStreamTime = System.nanoTime() - start;

        System.out.println("Operazioni: " + iterations);
        System.out.println("\nRisultati:");
        System.out.println("  Single Thread:    " + (singleThreadTime / 1_000_000) + " ms");
        System.out.println("  Multi Thread (4): " + (multiThreadTime / 1_000_000) + " ms");
        System.out.println("  Parallel Stream:  " + (parallelStreamTime / 1_000_000) + " ms");
        
        System.out.println("\nVerifica:");
        System.out.println("  Sum match: " + (sum == multiSum && multiSum == streamSum));

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===\n");

        System.out.println("1. PREFERIRE THREAD POOLS A new Thread():");
        System.out.println("   ✓ ExecutorService executor = Executors.newFixedThreadPool(4);");
        System.out.println("   ✗ new Thread(task).start() per ogni task");
        System.out.println("   Motivo: Riutilizzo thread, controllo risorse\n");

        System.out.println("2. SEMPRE SHUTDOWN EXECUTOR:");
        System.out.println("   ✓ executor.shutdown();");
        System.out.println("   ✓ executor.awaitTermination(timeout, unit);");
        System.out.println("   Motivo: Evita thread leak\n");

        System.out.println("3. GESTIRE InterruptedException CORRETTAMENTE:");
        System.out.println("   ✓ Thread.currentThread().interrupt(); // Ripristina flag");
        System.out.println("   ✗ Ignorare o solo printStackTrace");
        System.out.println("   Motivo: Preserva stato interruzione\n");

        System.out.println("4. PREFERIRE IMMUTABILITÀ:");
        System.out.println("   ✓ final fields, immutable objects");
        System.out.println("   ✓ Meno sincronizzazione necessaria");
        System.out.println("   Motivo: Thread-safe by design\n");

        System.out.println("5. USARE CONCURRENT COLLECTIONS:");
        System.out.println("   ✓ ConcurrentHashMap, CopyOnWriteArrayList");
        System.out.println("   ✗ synchronized(list) { list.add(...) }");
        System.out.println("   Motivo: Performance migliori\n");

        System.out.println("6. MINIMIZZARE SCOPE SYNCHRONIZED:");
        System.out.println("   ✓ synchronized(lock) { critical section }");
        System.out.println("   ✗ synchronized tutto il metodo");
        System.out.println("   Motivo: Riduce contention\n");

        System.out.println("7. EVITARE LOCK ORDERING PER DEADLOCK:");
        System.out.println("   ✓ Acquisire lock sempre nello stesso ordine");
        System.out.println("   ✓ Usare tryLock() con timeout");
        System.out.println("   ✗ Ordine casuale di lock\n");

        System.out.println("8. USARE CompletableFuture PER ASYNC:");
        System.out.println("   ✓ CompletableFuture.supplyAsync()");
        System.out.println("   ✓ Composizione con thenApply, thenCombine");
        System.out.println("   Motivo: Codice più leggibile\n");

        System.out.println("9. VOLATILE PER FLAGS:");
        System.out.println("   ✓ private volatile boolean running;");
        System.out.println("   Motivo: Visibility garantita\n");

        System.out.println("10. ATOMIC PER COUNTER:");
        System.out.println("    ✓ AtomicInteger counter = new AtomicInteger();");
        System.out.println("    ✗ synchronized(this) { counter++ }");
        System.out.println("    Motivo: Lock-free, più veloce\n");

        System.out.println("11. PARALLEL STREAMS CON CAUTELA:");
        System.out.println("    ✓ Per operazioni CPU-intensive su grandi dataset");
        System.out.println("    ✗ Per I/O operations o piccoli dataset");
        System.out.println("    Motivo: Overhead può superare benefici\n");

        System.out.println("12. THREAD NAMING:");
        System.out.println("    ✓ thread.setName(\"Worker-\" + id);");
        System.out.println("    Motivo: Debugging e monitoring\n");

        System.out.println("13. EXCEPTION HANDLING:");
        System.out.println("    ✓ try-catch dentro run()");
        System.out.println("    ✓ Thread.setUncaughtExceptionHandler()");
        System.out.println("    Motivo: Exception non propagano fuori thread\n");

        System.out.println("14. TESTING:");
        System.out.println("    ✓ CountDownLatch, CyclicBarrier per sync nei test");
        System.out.println("    ✓ Test con ThreadSanitizer/FindBugs");
        System.out.println("    Motivo: Race conditions difficili da riprodurre\n");

        System.out.println("15. DOCUMENTAZIONE:");
        System.out.println("    ✓ @GuardedBy per documentare lock");
        System.out.println("    ✓ Commenti su thread-safety");
        System.out.println("    Motivo: Manutenzione codice concorrente\n");
    }

    // ==================== UTILITY ====================

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
