import service.PublisherExample;
import service.SubscriberExample;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        int choice;
        do {
            System.out.println("1. Press One for Publishing Data");
            System.out.println("2. Press Two for Subscribing data");
            System.out.println("3. Exit");
            System.out.println();
            System.out.println("Choose Option");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter a data to publish");
                    JSONObject obj = new JSONObject();
                    System.out.println("Enter name of person : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    obj.put("name",name);

                    System.out.println("Enter age : ");
                    Integer age = sc.nextInt();
                    obj.put("age", age);

                    System.out.println("Enter salary : ");
                    Float salary = sc.nextFloat();
                    obj.put("salary", salary);

                    PublisherExample.publisherExample(
                            "projects/gcp-learning-342413/topics/my-topic", obj.toJSONString());
                    break;

                case 2:
                    SubscriberExample.subscriberExample("projects/gcp-learning-342413/subscriptions/my-sub");
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid Input!!!");
                    break;
            }

        }while(choice != 3);



    }
}
