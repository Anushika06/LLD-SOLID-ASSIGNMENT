public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validate(Notification n) {
        if (n.email == null || n.email.isEmpty()) {
            throw new IllegalArgumentException("Email address is required for Email");
        }
    }

    @Override
    protected void sendNotification(Notification n) {
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }

    @Override
    public String getChannel() {
        return "EMAIL";
    }
}