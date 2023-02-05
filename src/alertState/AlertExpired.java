package src.alertState;

public class AlertExpired implements AlertState {

    private boolean expired;

    public AlertExpired()
    {
        expired = true;
    }

    @Override
    public boolean getExpired() {
        return expired;
    }
    
}
