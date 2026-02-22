import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    protected ExportResult successfulExport(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):\n" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}