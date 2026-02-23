public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");
 
        NotificationSender[] senders = {
            new EmailSender(audit),
            new SmsSender(audit),
            new WhatsAppSender(audit),
            new PushNotificationSender(audit) 
        };

        for (NotificationSender sender : senders) {
            try {
                sender.send(n);
            } catch (IllegalArgumentException ex) {
                String code = sender.getChannel();
                System.out.println(code + " ERROR: " + ex.getMessage());
                audit.add(code + " failed");
            }
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}