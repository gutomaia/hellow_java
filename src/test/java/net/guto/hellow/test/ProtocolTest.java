/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributed under the terms of an GPLv3 license.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.test;

import junit.framework.TestCase;
import net.guto.hellow.protocol.Msnp8;

public class ProtocolTest extends TestCase {

	private Msnp8 msn;

	private MockConnectionHandle mockConnection;
	private MockAuthentication mockAuthentication;
	private MockCall mockCall;
	private MockClient mockClient;

	protected void setUp() throws Exception {
		mockConnection = new MockConnectionHandle();
		mockAuthentication = new MockAuthentication();
		mockCall = new MockCall();
		mockClient = new MockClient();
		msn = new Msnp8();
		msn.setConnectionHandle(mockConnection);
		msn.setAuthenticationHandle(mockAuthentication);
		msn.addConnectionListener(mockClient);
		msn.addContactListener(mockClient);
		msn.addCallListener(mockCall);
		msn.login("dvader@empire.com", "ih8jedis");
	}

	protected void tearDown() throws Exception {
		mockConnection = null;
		msn = null;
	}

	public void testChallenger() {
		String chl = "29409134351025259292";
		String digest = msn.challenger(chl);
		assertEquals("Challenger wasn't beated",
				"d0c1178c689350104350d99f8c36ed9c", digest);
	}

	private void receive(String msg) {
		msn.execute(msg);
	}

	private void send(String msg) {
		assertEquals("Send command is invalid", msg, mockConnection.sended);
	}

	public void testSession() {
		// Sends the MSN Client version
		// send("VER 1 MSNP8 CVR0\r\n");

		// Acknowledge
		receive("VER 1 MSNP8 CVR0\r\n");
		send("CVR 2 0x0409 win 4.10 i386 MSNMSGR 6.0.0602 MSMSGS dvader@empire.com\r\n");

		// Client sends information
		receive("CVR 2 6.0.0602 1.0.000 http://download.microsoft.com/download/8/a/4/\r\n");
		send("USR 3 TWN I dvader@empire.com\r\n");

		// Redirect
		receive("XFR 3 NS 207.46.106.118:1863 0 207.46.104.20:1863\r\n");
		send("VER 4 MSNP8 CVR0\r\n");
		assertEquals("Invalid host", "207.46.106.118", mockConnection.host);
		assertEquals("Invalid port", 1863, mockConnection.port);
		receive("VER 4 MSNP8 CVR0\r\n");
		send("CVR 5 0x0409 win 4.10 i386 MSNMSGR 6.0.0602 MSMSGS dvader@empire.com\r\n");
		receive("CVR 5 6.0.0602 6.0.0602 1.0.0000 http://download.microsoft.com/download/8/a/4/8a42bcae-f533-4468-b871-d2bc8dd32e9e/SETUP9x.EXE http://messenger.msn.com\r\n");
		send("USR 6 TWN I dvader@empire.com\r\n");
		receive("USR 6 TWN S lc=1033,id=507,tw=40,fs=1,ru=http%3A%2F%2Fmessenger%2Emsn%2Ecom,ct=1062764229,kpp=1,kv=5,ver=2.1.0173.1,tpf=43f8a4c8ed940c04e3740be46c4d1619\r\n");
		send("USR 7 TWN S t=53*1hAu8ADuD3TEwdXoOMi08sD*2!cMrntTwVMTjoB3p6stWTqzbkKZPVQzA5NOt19SLI60PY!b8K4YhC!Ooo5ug$$&p=5eKBBC!yBH6ex5mftp!a9DrSb0B3hU8aqAWpaPn07iCGBw5akemiWSd7t2ot!okPvIR!Wqk!MKvi1IMpxfhkao9wpxlMWYAZ!DqRfACmyQGG112Bp9xrk04!BVBUa9*H9mJLoWw39m63YQRE1yHnYNv08nyz43D3OnMcaCoeSaEHVM7LpR*LWDme29qq2X3j8N\r\n");
		assertFalse(
				"User not logged, ConnectionListener::onLogged shoudn't be called",
				mockClient.logged);

		// Logged
		receive("USR 7 OK dvader@empire.com Dart%20Vader 1 0\r\n");
		assertTrue(
				"User logged, ConnectionListener::onLogged should be called",
				mockClient.logged);
		send("SYN 1 0\r\n");
		receive("SYN 8 27 5 4\r\n");
		receive("GTC A\r\n");
		receive("BLP AL\r\n");
		receive("PRP PHH O1%20234\r\n");
		receive("PRP PHM 56%20789\r\n");
		assertEquals(mockClient.group, null);
		receive("LSG 0 Sifth\r\n");
		assertNotNull("Group is null and should be setted as Sifth",
				mockClient.group);
		assertEquals("Invalid group id", 0, mockClient.group.getId());
		assertEquals("Invalid group name", "Sifth", mockClient.group.getName());
		receive("LSG 1 Jedis\r\n");
		assertNotNull("Group is null and should be setted as Jedis",
				mockClient.group);
		assertEquals("Invalid group id", 1, mockClient.group.getId());
		assertEquals("Invalid group name", "Jedis", mockClient.group.getName());

		// Add Emperor as a contact
		assertEquals(this.mockClient.contact, null);
		receive("LST emperor@empire.com Emperor 13 0\r\n");
		assertNotNull(
				"Contact is null and should be setted as emperor@empire.com",
				mockClient.contact);
		assertEquals("emperor@empire.com", mockClient.contact.getUser());
		assertEquals("Emperor", mockClient.contact.getNick());
		assertEquals(13, mockClient.contact.getLists());
		assertNotNull(mockClient.contact.getGroups());
		assertEquals(1, mockClient.contact.getGroups().length);
		assertEquals(0, mockClient.contact.getGroups()[0]);
		receive("BPR MOB Y\r\n");

		// Add Luke as a contact
		mockClient.contact = null;
		receive("LST luke@rebels.org Luke 3 1\r\n");
		assertNotNull(
				"Contact is null and should be setted as luke@rebels.org",
				mockClient.contact);
		assertEquals(mockClient.contact.getUser(), "luke@rebels.org");
		assertEquals(mockClient.contact.getNick(), "Luke");
		assertEquals(mockClient.contact.getLists(), 3); // Luke dosen't have
														// Vader in their list!
														// Bastard!!
		assertNotNull(mockClient.contact.getGroups());
		assertEquals(1, mockClient.contact.getGroups().length);
		assertEquals(1, mockClient.contact.getGroups()[0]);

		send("CHG 9 NLN 0\r\n");
		receive("CHG 9 NLN 0\r\n");
		
		//Initial Presence
		receive("ILN 9 NLN emperor@empire.com Emperor 24\r\n");
		receive("ILN 9 IDL luke@rebels.org Luke 268435492\r\n");

		// Challenger
		receive("CHL 0 29409134351025259292\r\n");
		send("QRY 10 msmsgs@msnmsgr.com 32\r\nd0c1178c689350104350d99f8c36ed9c");

		// Presence
		receive("NLN NLN luke@rebels.org Luke%20JediMaster 268435492\r\n");//Available
		receive("NLN BSY luke@rebels.org Luke%20JediMaster 268435492\r\n");//Busy
		receive("NLN IDL luke@rebels.org Luke%20JediMaster 268435492\r\n");//Idle
		receive("NLN BRB luke@rebels.org Luke%20JediMaster 268435492\r\n");//Be Right Back
		receive("NLN AWY luke@rebels.org Luke%20JediMaster 268435492\r\n");//Away
		receive("NLN PHN luke@rebels.org Luke%20JediMaster 268435492\r\n");//On the Phone
		receive("NLN LUN luke@rebels.org Luke%20JediMaster 268435492\r\n");//Out to lunch
		receive("FLN luke@rebels.org\r\n");
		
		receive("FLN emperor@empire.com\r\n");
				
		// Call
		receive("RNG 876505971 65.54.228.15:1863 CKI 4216622.2513084 emperor@empire.com Emperor");
		assertEquals("876505971", mockCall.call);
		assertEquals("65.54.228.15", mockCall.server);
		assertEquals(1863, mockCall.port);
		assertEquals("4216622.2513084", mockCall.cki);
		assertEquals("emperor@empire.com", mockCall.username);
		assertEquals("Emperor", mockCall.nick);
	}
}