package tests;





import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import src.Alert;
import src.Topic;
import src.User;
import src.alertState.AlertExpired;

public class AlertTests {
    
    @Test
    public void UserCreatedSucessfullyTest()
    {
        User user = new User("Homero");

        String name = user.getName();

        assertEquals("Homero", name);
    }

    @Test
    public void RegisterUserIntoTopicSucessfullyTest()
    {
        Topic suspense = new Topic("Suspense", "Get out is out!");
        User user = new User("Luciano");

        suspense.register(user);

        assertEquals(suspense.getSubscribers().get(0), user);
    }

    @Test
    public void RegisterTopicsThatUserWillReceiveSucessfullyTest()
    {
        Topic action = new Topic("Action", "Matrix");
        User user = new User("Rodolfo");

        action.register(user);

        assertEquals(user.getDesiredTopicNames().get(0), "Action");
        
    }

    @Test
    public void SendAlertAboutATopicAndInterestedUsersReceiveItTest()
    {
        Topic horror = new Topic("Horror", "SMILE is broadcasting now!");
        Topic action = new Topic("Action", "Terminator is out!");
        User user = new User("Diego");
        User user2 = new User("Luciano");

        horror.register(user);
        horror.register(user2);

        action.changeBodyMessage("Terminator was good");
        horror.changeBodyMessage("SMILE has ceased broadcasting!");

        boolean wasActionAlertNotReceivedUser
         = user.getAlertsRelatedTo("Action").isEmpty();

        boolean wasActionAlertNotReceivedUser2 
        = user2.getAlertsRelatedTo("Action").isEmpty();
        
        assertEquals(wasActionAlertNotReceivedUser, wasActionAlertNotReceivedUser2);


    }

    @Test
    public void SendAlertToSpecificUserSucessfullyTest()
    {
        Topic suspense = new Topic("Romance", "Titanic is being remade.");
        User user = new User("Luciano");

        suspense.register(user);
        suspense.sendAlertTo(user);

        assertFalse(user.getAlerts().isEmpty());
    }

    @Test
    public void CheckIfExpiredAlertsAreNotShownToTheUserTest()
    {
        Topic topic = new Topic("Adventure", "Made in Abyss was really good.");
        User user = new User("Ricardo");

        topic.register(user);

        topic.changeBodyMessage("Testing");

        user.getAlerts().get(0).setExpiration(new AlertExpired());

        assertTrue(user.readAlerts().isEmpty());
    }

    @Test
    public void getAlertsThatHaveNotBeenReadFromUserTest()
    {
        Topic topic = new Topic("Action", "Tortugas ninja is pretty good!");
        User user = new User("Carlos");

        topic.register(user);

        topic.changeBodyMessage("The Matrix");

        assertFalse(user.readAlerts().isEmpty());
    }

    @Test
    public void MarkAlertsAsAlreadyReadTest()
    {
        Topic topic = new Topic("Suspense", "Testing suspense");
        User user = new User("Homero");

        topic.register(user);

        topic.changeBodyMessage("Second Test Suspense");

        // Read once
        user.readAlerts();

        // Check if alerts were read
        assertTrue(user.readAlerts().isEmpty());
    }

    @Test
    public void CheckThatUserAlertsAreReadInCorrectOrderTest()
    {
        

        Topic topic = new Topic("Mystery", "La chica del tren");
        User user = new User("Kevin");

        topic.register(user);

        topic.changeBodyMessage("La chica del bus");
        topic.changeBodyMessage("La chica del bus!");
        topic.changeBodyMessage("La chica del coche");

        // This should read urgent alerts first, and informative alerts later
        assertEquals(
        user.readAlerts().get(0).getAlertType().getType(), "Urgent"
        );

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void GetAllAlertsForATopicTest()
    {
        Topic topic = new Topic("Suspense", "Nobody is bad");
        Topic topic2 = new Topic("Suspense", "The Nun!");
        Topic topic3 = new Topic("Action", "The matrix");
        User user = new User("Walter");

        topic.register(user);
        topic2.register(user);
        topic3.register(user);

        topic.changeBodyMessage("Hello");
        topic3.changeBodyMessage("Action movie out!");
        topic2.changeBodyMessage("There!");
        


        List<Alert> alerts = user.getAlertsRelatedTo("Suspense");
        boolean isFirstAlertSuspense = alerts.get(0).getTopic().getName().equalsIgnoreCase("Suspense");
        boolean isSecondAlertSuspense = alerts.get(1).getTopic().getName().equals("Suspense");
        boolean isThirdAlertSuspense = alerts.get(2).getTopic().getName().equals("Action");


        assertEquals(isFirstAlertSuspense, isSecondAlertSuspense);
    }

}
