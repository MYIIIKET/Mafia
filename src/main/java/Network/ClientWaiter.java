package Network;


public class ClientWaiter extends Thread {
    private static boolean isTimeOut = false;
    private static int delayTime = 5000;

    public void run() {
        while (!isTimeOut) {
            delay();
            if (!isSomebodyConnected()) {
                isTimeOut = true;
            }
        }
    }

    private boolean isSomebodyConnected() {
        int currentConnections = Server.getTotalConnections();
        if (currentConnections - Server.getTotalConnections() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void delay() {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
