package aws.SNSandSES;

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AmazonSESSample implements RequestHandler<SNSEvent, Object> {
	
    static final String FROM = "akshaypmurgod@gmail.com";  
    static final String TO = "murgod.a@husky.neu.edu"; 
    
    static final String BODY = "Password reser link here";
    static final String SUBJECT = "You requested for password reset";

    /*
     * Before running the code:
     *      Fill in your AWS access credentials in the provided credentials
     *      file template, and be sure to move the file to the default location
     *      (/home/akshay/.aws/credentials) where the sample code will load the
     *      credentials from.
     *      https://console.aws.amazon.com/iam/home?#security_credential
     *
     * WARNING:
     *      To avoid accidental leakage of your credentials, DO NOT keep
     *      the credentials file in your source directory.
     */

	@Override
	public Object handleRequest(SNSEvent request, Context context) {
        //Received from /reset http body
		//String email = request.getRecords().get(0).getSNS().getMessage();

		System.out.println("context ----->" +context);
		System.out.println("request ----->" +request);
		System.out.println("Email from reset"+ "email");

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{TO});

        // Create the subject and body of the message.
        Content subject = new Content().withData(SUBJECT);
        Content textBody = new Content().withData(BODY);
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest Emailequest = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            /*
             * The ProfileCredentialsProvider will return your [default]
             * credential profile by reading from the credentials file located at
             * (/home/akshay/.aws/credentials).
             *
             * TransferManager manages a pool of threads, so we create a
             * single instance and share it throughout our application.
             */
    		String AccessKey ="AKIAICBBWL6MHJZATOJQ";
    		String SecretAccessKey ="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";
    		System.out.println("New Deployment number 1");
    		BasicAWSCredentials basicAwsCredentials = null;
    		
            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            try {
                //credentialsProvider.getCredentials();
                basicAwsCredentials = new BasicAWSCredentials(AccessKey,SecretAccessKey);
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                        "Please make sure that your credentials file is at the correct " +
                        "location (/home/akshay/.aws/credentials), and is in valid format.",
                        e);
            }

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAwsCredentials))
                // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
                // access status, sending limits, and Amazon SES identity-related settings are specific to a given
                // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
                // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
                // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
                .withRegion("us-east-1")
                .build();

            // Send the email.
            client.sendEmail(Emailequest);
            System.out.println("Email sent!");

        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        return null;
    }
}
