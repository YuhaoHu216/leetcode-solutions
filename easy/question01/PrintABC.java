package question01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按顺序输出ABC
 */
public class PrintABC {

    private static int state = 0;
    private static Lock lock = new ReentrantLock();

    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static Condition c3 = lock.newCondition();

    public static void main(String[] args) {

        new Thread(() -> print("A", 0, c1, c2)).start();
        new Thread(() -> print("B", 1, c2, c3)).start();
        new Thread(() -> print("C", 2, c3, c1)).start();
    }

    private static void print(String str, int target,
                              Condition current, Condition next) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (state % 3 != target) {
                    current.await();
                }
                System.out.print(str);
                state++;
                next.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
