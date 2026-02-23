public class JsonExporter extends Exporter {

    @Override
    protected ExportResult successfulExport(ExportRequest req) {

        String json = "{ \"title\": \"" + req.title +
                "\", \"body\": \"" + req.body + "\" }";

        return new ExportResult("application/json",
                json.getBytes());
    }
}