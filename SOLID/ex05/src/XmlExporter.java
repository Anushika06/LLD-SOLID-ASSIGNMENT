import java.nio.charset.StandardCharsets;

public class XmlExporter extends Exporter {

    @Override
   protected ExportResult successfulExport(ExportRequest req) {

        String xml =
                "<report>" +
                "<title>" + req.title + "</title>" +
                "<body>" + req.body + "</body>" +
                "</report>";

        return new ExportResult(
                "application/xml",
                xml.getBytes(StandardCharsets.UTF_8)
        );
    }
}