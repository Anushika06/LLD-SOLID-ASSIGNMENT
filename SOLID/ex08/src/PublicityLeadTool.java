public class PublicityLeadTool implements EventPromotionOps {

    private final EventPlanner planner;

    public PublicityLeadTool(EventPlanner planner) {
        this.planner = planner;
    }

    @Override
    public void promoteEvent(String name) {
        planner.promote(name);
    }
}