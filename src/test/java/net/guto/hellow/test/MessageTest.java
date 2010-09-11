package net.guto.hellow.test;

import junit.framework.TestCase;
import net.guto.hellow.core.pojos.Message;

public class MessageTest extends TestCase {

	private Message message;

	@Override
	protected void tearDown() throws Exception {
		message = null;
	}
	
	public void testMsgReveiceTypingUser() {
		String command = "MSG dvader@empire.com Vader 90\r\n"
				+ "MIME-Version: 1.0\r\n"
				+ "Content-Type: text/x-msmsgscontrol\r\n"
				+ "TypingUser: dvader@empire.com\r\n" + "\r\n" + "\r\n";
		message = new Message(command);
		assertNotNull(message.getHeader());
		assertEquals("1.0", message.getHeader().get("MIME-Version"));
		assertEquals("text/x-msmsgscontrol",
				message.getHeader().get("Content-Type"));
		assertEquals("dvader@empire.com", message.getHeader().get("TypingUser"));
		assertEquals("\r\n", message.getBody());
	}

	public void testMsgReceiveHello() {
		String command = "MSG dvader@empire.com Vader 143\r\n"
				+ "MIME-Version: 1.0\r\n"
				+ "Content-Type: text/plain; charset=UTF-8\r\n"
				+ "X-MMS-IM-Format: FN=Lucida%20Sans%20Unicode; EF=B; CO=ff0000; CS=0; PF=22\r\n"
				+ "\r\n" + "Hello.";
		message = new Message(command);
		assertNotNull(message.getHeader());
		assertEquals("1.0", message.getHeader().get("MIME-Version"));
		assertEquals("text/plain; charset=UTF-8",
				message.getHeader().get("Content-Type"));
		assertEquals(
				"FN=Lucida%20Sans%20Unicode; EF=B; CO=ff0000; CS=0; PF=22",
				message.getHeader().get("X-MMS-IM-Format"));
		assertEquals("Hello.", message.getBody());
	}

	public void testMsgSendTypingUser() {
		String command = "MSG 2 U 90\r\n" + "MIME-Version: 1.0\r\n"
				+ "Content-Type: text/x-msmsgscontrol\r\n"
				+ "TypingUser: dvader@empire.com\r\n" + "\r\n" + "\r\n";
		message = new Message(2, "dvader@empire.com");
		assertEquals(command, message.send());
	}

	public void testMsgSendAreYouThere() {
		String command = "MSG 5 N 138\r\n"
				+ "MIME-Version: 1.0\r\n"
				+ "Content-Type: text/plain; charset=UTF-8\r\n"
				+ "X-MMS-IM-Format: FN=MS%20Sans%20Serif; EF=; CO=0; CS=0; PF=0\r\n"
				+ "\r\n" + "Are you there?";
		message = new Message("Are you there?", 5);
		assertEquals(command, message.send());
	}
	
	public void testMsnSendILikeTurtles(){
		String command = "MSG 8 A 139\r\n"+
	    "MIME-Version: 1.0\r\n"+
	    "Content-Type: text/plain; charset=UTF-8\r\n"+
	    "X-MMS-IM-Format: FN=MS%20Sans%20Serif; EF=; CO=0; CS=0; PF=0\r\n"+
	    "\r\n"+
	    "I like turtles.";
		message = new Message("I like turtles.", 8);
		//assertEquals(command, message.send());
	}
}
