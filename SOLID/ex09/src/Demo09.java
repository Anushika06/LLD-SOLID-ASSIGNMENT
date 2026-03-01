public class Demo09 {
    public static void main(String[] args) {

        System.out.println("=== Evaluation Pipeline ===");

        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        PlagService plagiarism = new PlagiarismChecker();
        GradingService grader = new CodeGrader(new Rubric());
        // GradingService lenientGrader = new LenientCodeGrader();
        ReportService writer = new ReportWriter();

        EvaluationPipeline pipeline = new EvaluationPipeline(plagiarism, grader, writer);

        // EvaluationPipeline lenientPipeline = new EvaluationPipeline(plagiarism, lenientGrader, writer);

        pipeline.evaluate(sub);
        // lenientPipeline.evaluate(sub);
    }
}