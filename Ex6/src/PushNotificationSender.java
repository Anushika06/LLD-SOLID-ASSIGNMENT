public class PushNotificationSender extends NotificationSender {
    
    public PushNotificationSender(AuditLog audit) { 
        super(audit); 
    }

    @Override
    public String getChannel() { 
        return "PUSH"; 
    }

    @Override
    protected void validate(Notification n) {
        if (n.email == null || n.email.isEmpty()) {
            throw new IllegalArgumentException("Account email is required to find device token");
        }
    }

    @Override
    protected void sendNotification(Notification n) {
        System.out.println("PUSH -> deviceOwner=" + n.email + " title=" + n.subject + " message=" + n.body);
        audit.add("push sent");
    }
}