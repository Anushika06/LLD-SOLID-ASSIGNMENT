public class ExportResult {

    public final String contentType;
    public final byte[] bytes;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "OK bytes=" + bytes.length;
    }

    public static ExportResult error(String msg) {
        return new ExportResult("ERROR", msg.getBytes());
    }
}