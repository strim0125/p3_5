import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
        private static int CARS_COUNT;
        private Race race;
        private int speed;
        private String name;

        public String getName() {
            return name;
        }
        public int getSpeed() {
            return speed;
        }

        public Car(Race race, int speed, CyclicBarrier barrier) {
            this.race = race;
            this.speed = speed;
            CARS_COUNT++;
            this.name = "Участник #" + CARS_COUNT;
        }
        @Override
        public void run() {
            try {
                System.out.println(this.name + " готовится");
                Semaphore sem = new Semaphore(speed);
                Thread.sleep(500 + (int)(Math.random() * 800));
                System.out.println(this.name + " готов");
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
        }
}