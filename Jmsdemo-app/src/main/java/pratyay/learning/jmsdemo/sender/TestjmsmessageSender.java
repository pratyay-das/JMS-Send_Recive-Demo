/**
 * 
 */
package pratyay.learning.jmsdemo.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author praty
 *
 */
public class TestjmsmessageSender {

	/**
	 * @param args
	 * @throws NamingException
	 * @throws JMSException
	 */
	public void sendJmsQueseMessages(int count) throws NamingException, JMSException {
		Context ctx = new InitialContext();
		ConnectionFactory confac = (ConnectionFactory) ctx.lookup("jms/pratyayDemoJms-Connection-Factory-Jndi");
		Connection con = confac.createConnection();
		Session conSession = con.createSession(Session.AUTO_ACKNOWLEDGE);
		Destination dest = (Queue) ctx.lookup("jms/pratyayLearningDemoJmsDestination");
		MessageProducer producer = conSession.createProducer(dest);
		TextMessage message = conSession.createTextMessage("another sent messeage number "+count++);
		producer.send(message);
		System.out.println(message.getText());

		conSession.close();
		con.close();
		ctx.close();
	}

	public static void main(String[] args) throws NamingException, JMSException {
		// TODO Auto-generated method stub
		int count=0;
		TestjmsmessageSender test = new TestjmsmessageSender();
		while(count <11) {
		test.sendJmsQueseMessages(count);
		count++;
		}
	}

}
