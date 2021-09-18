package horzsolt.javaplayground;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/*
A possible task for a coding interview
 */
class CircuitBreakerExecutor<R> {

    private CircuitBreaker circtuitBreaker;
    private Supplier<String> task = () -> "task is done";
    private Supplier<String> fallback = () -> "fallback";

    private CircuitBreaker cb = new CircuitBreaker();

    public R run(Supplier<R> task, Supplier<R> fallback) {
        return cb.execute(task, fallback);
    }

    class CircuitBreaker{

        private AtomicInteger successExec = new AtomicInteger(0);
        private AtomicInteger allExec = new AtomicInteger(0);
        private AtomicInteger failedExec = new AtomicInteger(0);
        private AtomicInteger halfOpenSuccessfulExec = new AtomicInteger(0);
        private CircuitState state = CircuitState.OPEN;
        private LocalDateTime circuitClosed;
        private final long openDurationInSeconds = 500;
        private final double failureRate = 0.3;
        private final int openCapacity = 10;

        //    succesRate: request success rate required for moving from half open to open state
        //    failureRate: request failure rate e.g. 0.3 if we go above open the circuit
        //    openDurationInMs: in case we close the circuit we will wait until this duration before half opening
        //    openCapacity: number of requests taken into consideration for opening the circuit from half open state

        public R execute(Supplier<R> task, Supplier<R> fallback) {

            R result = (R) "";
            allExec.incrementAndGet();

            switch (state) {
                case OPEN:
                    try {
                        result = task.get();
                    } catch (Exception e) {
                        failedExec.incrementAndGet();

                        if (failedExec.get()/allExec.get() > failureRate) {
                            circuitClosed = LocalDateTime.now();
                            state = CircuitState.CLOSED;
                            halfOpenSuccessfulExec.set(0);
                        }
                        throw e;
                    }
                case CLOSED:
                    if (circuitClosed.plusSeconds(openDurationInSeconds).isAfter(LocalDateTime.now())) {
                        try {
                            result = task.get();
                            halfOpenSuccessfulExec.incrementAndGet();
                            state = CircuitState.HALF_OPEN;
                        } catch (Exception e) {
                            failedExec.incrementAndGet();
                            halfOpenSuccessfulExec.set(0);
                        }
                    }
                case HALF_OPEN:
                    try {
                        result = task.get();
                        if (halfOpenSuccessfulExec.incrementAndGet() > openCapacity) {
                            state = CircuitState.OPEN;
                        }
                    } catch (Exception e) {
                        failedExec.incrementAndGet();

                        if (failedExec.get()/allExec.get() > failureRate) {
                            circuitClosed = LocalDateTime.now();
                            state = CircuitState.CLOSED;
                            halfOpenSuccessfulExec.set(0);
                        }
                        throw e;
                    }

            }

            return result;
        }
    }

    enum CircuitState {
        OPEN,
        HALF_OPEN,
        CLOSED
    }

    public static void main(String[] args) {

    }
}
