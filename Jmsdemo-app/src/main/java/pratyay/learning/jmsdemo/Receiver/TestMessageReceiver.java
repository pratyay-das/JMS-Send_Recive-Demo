/**
 * 
 */
package pratyay.learning.jmsdemo.Receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author praty
 *
 */
public class TestMessageReceiver {

	/**
	 * @param args
	 * @throws NamingException
	 * @throws JMSException
	 */

	public void receiverMessage() throws NamingException, JMSException {
		Context context = new InitialContext();
		ConnectionFactory conectFac = (ConnectionFactory) context.lookup("jms/pratyayDemoJms-Connection-Factory-Jndi");
		Connection con = conectFac.createConnection();
		Session conectSession = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = (Destination) context.lookup("jms/pratyayLearningDemoJmsDestination");
		MessageConsumer mconsumer = conectSession.createConsumer(dest);
		con.start();
		TextMessage message = (TextMessage) mconsumer.receive();
		System.out.println(message.getText()+ " Received");
		conectSession.close();
		con.close();
		context.close();
	}

	public static void main(String[] args) throws NamingException, JMSException {
		// TODO Auto-generated method stub
		TestMessageReceiver receiver = new TestMessageReceiver();
		while(true) {
				receiver.receiverMessage();
		}

	}

}
