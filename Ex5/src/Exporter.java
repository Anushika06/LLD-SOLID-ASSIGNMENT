public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("ExportRequest cannot be null");
        }
        if (req.title == null || req.body == null) {
            throw new IllegalArgumentException("ExportRequest title and body cannot be null");
        }
        
        return successfulExport(req);
    }

    protected abstract ExportResult successfulExport(ExportRequest req);
}