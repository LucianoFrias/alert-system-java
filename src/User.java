package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class User {
    
    private String name;
    private List<Alert> alerts;
    private List<String> desiredTopicNames;

    public User(String name)
    {
        this.name = name;
        this.alerts = new ArrayList<Alert>();
        this.desiredTopicNames = new ArrayList<String>();
    }

    public String getName()
    {
        return name;
    }

    public List<Alert> getAlerts()
    {
        return alerts;
    }

    public List<String> getDesiredTopicNames()
    {
        return desiredTopicNames;
    }

    public void addAlert(Alert alert)
    {
        alerts.add(alert);
    }


    public void addDesiredTopic(String topicName)
    {
        boolean repeated = false;
        for (String nameofTopic : desiredTopicNames) {
            if (nameofTopic.equals(topicName))
            {
                repeated = true;
            }
        }

        if (!repeated)
        {
            desiredTopicNames.add(topicName);
        }
    }

    public void removeDesiredTopic(String topicName)
    {
        desiredTopicNames.remove(topicName);
    }

    public List<Alert> readAlerts()
    {
        Stack<Alert> unreadAlerts = new Stack<Alert>();
        Stack<Alert> informativeAlerts = new Stack<Alert>();
        Stack<Alert> urgentAlerts = new Stack<Alert>();


        for (Alert alert : alerts) {
            if (!alert.getIsAlreadyRead() && !alert.getExpirationState().getExpired())
            {
                alert.setIsAlreadyRead(true);

                if (alert.getAlertType().getType().equals("Urgent"))
                {
                    urgentAlerts.add(alert);
                }
                else if (alert.getAlertType().getType().equals("Informative"))
                {
                    informativeAlerts.add(alert);
                }
                
            }
        }

        unreadAlerts.addAll(urgentAlerts);
        unreadAlerts.addAll(informativeAlerts);

        return unreadAlerts;
    }

    public List<Alert> getAlertsRelatedTo(String topicName)
    {
        Stack<Alert> topicAlerts = new Stack<Alert>();
        Stack<Alert> informativeAlerts = new Stack<Alert>();
        Stack<Alert> urgentAlerts = new Stack<Alert>();

        for (Alert alert : alerts) {
            if (!alert.getIsAlreadyRead() && !alert.getExpirationState().getExpired())
            {
                if (alert.getTopic().getName().equals(topicName))
                {
                    
                    if (alert.getAlertType().getType().equals("Urgent"))
                    {
                        urgentAlerts.add(alert);
                    }
                    else if (alert.getAlertType().getType().equals("Informative"))
                    {
                        informativeAlerts.add(alert);
                    }
                }
            }
        }

        topicAlerts.addAll(urgentAlerts);
        topicAlerts.addAll(informativeAlerts);

        return topicAlerts;
    }
    
}
