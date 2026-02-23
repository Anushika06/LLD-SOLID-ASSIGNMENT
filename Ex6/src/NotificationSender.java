public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) { 
        this.audit = audit; 
    }

    public final void send(Notification n) {
        if (n == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }
        if (n.body == null || n.body.trim().isEmpty()) {
            throw new IllegalArgumentException("Notification body is required");
        }        
        validate(n); 
        sendNotification(n);
    }
    protected abstract void validate(Notification n);
    protected abstract void sendNotification(Notification n);
    public abstract String getChannel();
}