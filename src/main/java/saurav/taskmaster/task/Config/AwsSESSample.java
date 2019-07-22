package saurav.taskmaster.task.Config;

import java.io.IOException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

/*
Reference https://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-sdk-java.html
 */

public class AwsSESSample {

    public  void sendEmail(String taskName) {
        String FROM = "sk.sdsu@gmail.com";
        String TO = "kadariya@uw.edu";
         String CONFIGSET = "ConfigSet";
        // The subject line for the email.
        String SUBJECT = " Task: "+ taskName + " is completed";
        String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
                ;
        String TEXTBODY = taskName + " is completed";

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.US_WEST_2).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);
                    // Comment or remove the next line if you are not using a
                    // configuration set
                    //.withConfigurationSetName(CONFIGSET);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}