public class Main {

    public static void main(String[] args) {

        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());

        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        System.out.println("PDF: " + pdf.export(req));
        System.out.println("CSV: " + csv.export(req));
        System.out.println("JSON: " + json.export(req));
    }
}