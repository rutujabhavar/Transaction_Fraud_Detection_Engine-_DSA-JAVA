import java.util.*;

class Transaction {
    String userId;
    double amount;
    String location;
    long timestamp;
    String deviceId;

    public Transaction(String userId, double amount, String location, long timestamp, String deviceId) {
        this.userId = userId;
        this.amount = amount;
        this.location = location;
        this.timestamp = timestamp;
        this.deviceId = deviceId;
    }
}

class FraudDetector {

    private static final double HIGH_AMOUNT_THRESHOLD = 50000.0;
    private static final int MAX_TXN_1_MIN = 5;
    private static final long ONE_MINUTE = 60 * 1000;

    Map<String, Queue<Transaction>> recentTxns = new HashMap<>();
    Map<String, Double> avgSpend = new HashMap<>();
    Set<String> blacklistedDevices = new HashSet<>();

    public FraudDetector() {
        blacklistedDevices.add("DEV-999");
        blacklistedDevices.add("DEV-123");
    }

    public boolean isFraud(Transaction t) {

        double riskScore = 0;

        // RULE 1: High Amount
        if (t.amount > HIGH_AMOUNT_THRESHOLD) {
            riskScore += 50;
            System.out.println("High amount detected");
        }

        // RULE 2: Blacklisted Device
        if (blacklistedDevices.contains(t.deviceId)) {
            riskScore += 80;
            System.out.println("Blacklisted device detected");
        }

        // RULE 3: Too Many Transactions Quickly (Sliding Window)
        recentTxns.putIfAbsent(t.userId, new LinkedList<>());
        Queue<Transaction> queue = recentTxns.get(t.userId);

        while (!queue.isEmpty() && (t.timestamp - queue.peek().timestamp) > ONE_MINUTE) {
            queue.poll();
        }

        queue.add(t);

        if (queue.size() > MAX_TXN_1_MIN) {
            riskScore += 40;
            System.out.println("High transaction frequency detected");
        }

        // RULE 4: Spending Anomaly
        double avg = avgSpend.getOrDefault(t.userId, t.amount);
        if (t.amount > avg * 3) {
            riskScore += 35;
            System.out.println("Spending anomaly detected");
        }

        // Update average spending (simple incremental)
        avgSpend.put(t.userId, (avg + t.amount) / 2);

        // RULE 5: Sudden Location Change (Optional extension)
        // TODO: Can add geolocation distance check

        System.out.println("Risk Score = " + riskScore);

        return riskScore >= 60;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FraudDetector fd = new FraudDetector();

        Transaction t1 = new Transaction("user1", 2000, "Mumbai", System.currentTimeMillis(), "DEV-001");
        System.out.println("Fraud? " + fd.isFraud(t1));

        Transaction t2 = new Transaction("user1", 60000, "Delhi", System.currentTimeMillis(), "DEV-001");
        System.out.println("Fraud? " + fd.isFraud(t2));

        Transaction t3 = new Transaction("user1", 55000, "Delhi", System.currentTimeMillis(), "DEV-999");
        System.out.println("Fraud? " + fd.isFraud(t3));

        for (int i = 0; i < 6; i++) {
            Transaction tx = new Transaction("user1", 2000, "Mumbai", System.currentTimeMillis(), "DEV-001");
            System.out.println("Fraud? " + fd.isFraud(tx));
        }
    }
}
