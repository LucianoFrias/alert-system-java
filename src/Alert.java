package src;

import java.time.LocalDateTime;

import src.alertState.AlertExpired;
import src.alertState.AlertNotExpired;
import src.alertState.AlertState;
import src.alertType.AlertType;
import src.alertType.InformativeAlert;
import src.alertType.UrgentAlert;

public class Alert {

    private Topic topic;
    private String description;
    private boolean isAlreadyRead;

    private LocalDateTime currentTime;
    private LocalDateTime expirationTime;
    private AlertState expirationState;
    private AlertType alertType;

    public Alert(Topic topic, String description)
    {
        this.topic = topic;
        this.description = description;
        this.isAlreadyRead = false;
        this.alertType = setAlertType();

        currentTime = LocalDateTime.now();
        expirationTime = currentTime.plusMinutes(30);

        checkExpiration();
    }

    public Topic getTopic()
    {
        return topic;
    }

    public String getDescription()
    {
        return description;
    }

    public void setIsAlreadyRead(boolean state)
    {
        this.isAlreadyRead = state;
    }

    public boolean getIsAlreadyRead()
    {
        return isAlreadyRead;
    }

    public AlertState getExpirationState()
    {
        return expirationState;
    }

    public AlertType setAlertType()
    {
        AlertType alertType = null;

        if (this.description.contains("!"))
        {
            alertType = new UrgentAlert();
        }
        else
        {
            alertType = new InformativeAlert();
        }

        return alertType;
    }

    public AlertType getAlertType()
    {
        return alertType;
    }

    public boolean checkExpiration()
    {
        this.expirationState =
        currentTime.isBefore(expirationTime)
        ? new AlertNotExpired()
        : new AlertExpired();

        return expirationState.getExpired();
    }

    public void setExpiration(AlertState state)
    {
        this.expirationState = state;
    }

}


