public class PlagiarismChecker implements PlagService {
    @Override
    public int check(Submission s) {
        return (s.code.contains("class") ? 12 : 40);
    }
}