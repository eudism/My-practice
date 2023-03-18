package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements IEmailSender {
	@Value("${outgoingMailServer}")
	String outgoingMailServer;
	@Autowired
	private ILogger logger;

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	public void sendEmail (String email, String message){
		System.out.println("EmailSender: sending '"+message+"' to "+email );
		System.out.println("sending email to the server... " + outgoingMailServer);
		logger.log("Email is sent: message= "+ message +" , emailaddress ="+ email  );
	}
}
