import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult successfulExport(ExportRequest req) {
        String body = req.body;
        body = body.replace("\"", "\"\"");
        body = "\"" + body + "\"";

        String csv = "title,body\n" + req.title + "," + body;

        return new ExportResult(
                "text/csv",
                csv.getBytes(StandardCharsets.UTF_8)
        );
    }
}