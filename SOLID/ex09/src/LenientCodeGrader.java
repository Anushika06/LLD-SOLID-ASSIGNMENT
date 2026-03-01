public class LenientCodeGrader implements GradingService {

    @Override
    public int grade(Submission s) {
        // very generous scoring
        return 85 + (s.code.length() % 10);
    }
}