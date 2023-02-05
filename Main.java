import src.Topic;
import src.User;

public class Main {
 
    public static void main(String[] args) {
        
        Topic action = new Topic("Action", "The New Terminator movie has begun!");

        Topic horror = new Topic("Horror", "The Nun is bad!");

        Topic action2 = new Topic("Action", "god");


        User user1 = new User("Luciano");
        User user2 = new User("Homero");

        action.register(user1);
        action2.register(user1);

        horror.register(user1);
        horror.register(user2);
       

        horror.changeBodyMessage("The nun is HORRIBLE");

        action2.changeBodyMessage("Terminator is out");;

        action.changeBodyMessage("Terminator is amazing");
        
        horror.changeBodyMessage("The nun is good");

        horror.changeBodyMessage("Halloween is out!");

        horror.sendAlertTo(user2);
     
    }
}
