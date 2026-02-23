public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    protected void validate(Notification n) {
        if (n.phone == null || n.phone.isEmpty()) {
            throw new IllegalArgumentException("Phone number is required for SMS");
        }
    }

    @Override
    protected void sendNotification(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
    @Override
    public String getChannel() {
        return "SMS";
    }
}