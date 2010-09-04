package net.guto.hellow.protocol;

import net.guto.hellow.core.ConnectionHandle;
import net.guto.hellow.core.SocketConnection;
import net.guto.hellow.core.listener.CommandListener;

public abstract class Msnp { //implements Runnable {

	protected final String EL = "\r\n";
	protected String _passport = null;
	protected int _trid;

	abstract String getHost();

	abstract Integer getPort();

	abstract void execute(String command);

	private ConnectionHandle _connectionHandle;


	public Msnp() {
		_connectionHandle = new SocketConnection();
	}

	public Msnp(ConnectionHandle connectionHandle) {
		_connectionHandle = connectionHandle;
	}

	protected void connect(String host, int port) {
		_connectionHandle.connect(host, port);
	}

	protected void disconnect() {
		_connectionHandle.disconnect();
	}
	
	public void setConnectionHandle(ConnectionHandle connectionHandle){
		_connectionHandle = connectionHandle;
	}

	private CommandListener _commandListener = null;

	public void addCommandListener(CommandListener commandListener) {
		_commandListener = commandListener;
	}
	
	

	protected void send(String command) {
		_connectionHandle.send(command);
		if (_commandListener != null && command != null)
			_commandListener.sendedCommand(command);
	}

//	public String nextCommand() {
//		String cmd = null;
//		if (getSocket() != null) {
//			try {
//				cmd = bufferedreader.readLine();
//				// Payload CMD
//				if (cmd != null && cmd.startsWith("MSG")) {
//					String tokens[] = cmd.split(" ");
//					int len = Integer.valueOf(tokens[3]);
//					char[] cbuf = new char[len];
//					bufferedreader.read(cbuf, 0, len);
//					cmd += EL + new String(cbuf);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if (_commandListener != null && cmd != null)
//			_commandListener.reveivedCommand(cmd + EL);
//		return cmd;
//	}

//	@Override
//	public void run() {
//		listen();
//	}
	
	protected final void listen() {
		while (_connectionHandle.hasMoreCommands()) {
			String command = _connectionHandle.nextCommand();
			if (!command.trim().equals("")){
				execute(command);
			}
		}
	}


//	public void listen2() {
//		boolean cont = true;
//		int j = 0;
//		while (cont) {
//			String cmd = nextCommand();
//			if (cmd != null && cmd != "") {
//				StringTokenizer token = new StringTokenizer(cmd);
//				String tokens[] = new String[token.countTokens()];
//				int i = 0;
//				while (token.hasMoreTokens()) {
//					tokens[i] = token.nextToken();
//					i++;
//				}
//				//execute(tokens[0], tokens);
//				j++;
//			}
//		}
//	}

	/*
	 * function listen() { $cont = true; while ($cont) { $cmd =
	 * $this->nextCommand(); if (!empty ($cmd)) { // substr($msg,0,3) == 'MSG'
	 * $tokens = explode(" ", $cmd); $this->execute($tokens[0], $tokens);
	 * flush(); } // echo ($endtime - $initime) ."<br>"; if
	 * (!$this->getSocket()) { $cont = false; } } $this->logout(); }
	 * 
	 * function listen2() { $i = 0; if ($this->getSocket()){ for ($cmd =
	 * $this->nextCommand(); !empty($cmd);$cmd = $this->nextCommand(), $i++){
	 * $tokens = explode(" ", $cmd); $this->execute($tokens[0], $tokens);
	 * flush(); if (!$this->getSocket()) { return false; } echo $i; if ($i ==
	 * 100){ echo 'loop no listen 2'; break; } } return true; } return false; }
	 * 
	 * 
	 * public String out() { return "OUT"; } // username password lc
	 */
}
