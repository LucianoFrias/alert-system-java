package src;

import java.util.ArrayList;
import java.util.List;


public class Topic {
    
    private String name;
    private String bodyMessage;
    private List<User> subscribers;

    public Topic(String name, String bodyMessage)
    {
        this.name = name;
        this.bodyMessage = bodyMessage;
        subscribers = new ArrayList<User>();
    }

    public String getName()
    {
        return name;
    }   

    public String getDescription()
    {
        return bodyMessage;
    }

    public List<User> getSubscribers()
    {
        return subscribers;
    }

    public void setBodyMessage(String bodyMessage)
    {
        this.bodyMessage = bodyMessage;
    }

    public void register(User user)
    {
        subscribers.add(user);
        user.addDesiredTopic(name);
    }
    
    public void unregister(User user)
    {
        subscribers.remove(user);
        user.removeDesiredTopic(name);
    }


    public void notifySubscribers()
    {
        for (User subscriber : subscribers) {
            for (int i = 0; i < subscriber.getDesiredTopicNames().size(); i++)
            {
                if (subscriber.getDesiredTopicNames().get(i).equals(name))
                {
                    subscriber.addAlert(new Alert(this, bodyMessage));

                }
            }
        }
    }

    public void sendAlertTo(User user)
    {

        for (User subscriber : subscribers) {

            if (subscriber == user)
            {
                for (int i = 0; i < subscriber.getDesiredTopicNames().size(); i++)
                {
                    if (subscriber.getDesiredTopicNames().get(i).equals(name))
                    {
                        subscriber.addAlert(new Alert(this, bodyMessage));
                    }
                }
            }
        }
    }

    public void changeBodyMessage(String bodyMessage)
    {
		setBodyMessage(bodyMessage);
		notifySubscribers();
    }


}
