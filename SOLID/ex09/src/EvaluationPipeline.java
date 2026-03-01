public class EvaluationPipeline {

    private final PlagService plagiarismService;
    private final GradingService gradingService;
    private final ReportService reportService;

    public EvaluationPipeline(
            PlagService plagiarismService,
            GradingService gradingService,
            ReportService reportService) {

        this.plagiarismService = plagiarismService;
        this.gradingService = gradingService;
        this.reportService = reportService;
    }

    public void evaluate(Submission sub) {

        int plag = plagiarismService.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = gradingService.grade(sub);
        System.out.println("CodeScore=" + code);

        String reportName = reportService.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}