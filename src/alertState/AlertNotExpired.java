package src.alertState;

public class AlertNotExpired implements AlertState {

    private boolean expired;

    public AlertNotExpired()
    {
        expired = false;
    }

    @Override
    public boolean getExpired() {
        return expired;
    }
    
}
