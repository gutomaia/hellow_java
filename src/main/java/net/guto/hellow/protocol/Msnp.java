package net.guto.hellow.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import net.guto.hellow.core.listener.CommandListener;

public abstract class Msnp implements Runnable {

	protected final String EL = "\r\n";
	protected String _passport = null;
	protected int _trid;

	private PrintWriter printwriter = null;
	private BufferedReader bufferedreader = null;

	abstract String getHost();

	abstract Integer getPort();

	abstract void execute(String cmd, String... params);

	protected Socket socket = null;

	private synchronized Socket getSocket() {
		return socket;
	}

	protected void connect(String host, int port) {
		try {
			if (socket != null && socket.isConnected()) {
				socket.close();
				socket = null;
			}
			socket = new Socket(host, port);
			printwriter = new PrintWriter(socket.getOutputStream(), true);
			bufferedreader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			_trid = 1;
		} catch (UnknownHostException unknownhostexception) {
			System.err
					.println("Exception: Unknown Host - Network unavailable?");
			System.exit(1);
		} catch (IOException ioexception) {
			System.err.println("Exception: Can't get input from network");
			System.exit(1);
		}
	}

	protected void disconnect() {
		synchronized (socket) {
			try {
				socket.close();
				socket = null;
			} catch (Exception e) {
				socket = null;
			}
		}
	}

	private CommandListener _commandListener = null;

	public void addCommandListener(CommandListener commandListener) {
		_commandListener = commandListener;
	}

	protected void send(String command) {
		try {
		if (getSocket() != null) {
			//printwriter.printf(command);
			printwriter.write(command);
			printwriter.flush();
			_trid++;
			if (_commandListener != null && command != null)
				_commandListener.sendedCommand(command);
		} else {
			System.out.println("ERROR SEM SOCKET");
		}}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(command);
			// TODO: handle exception
		}
	}

	public String nextCommand() {
		String cmd = null;
		if (getSocket() != null) {
			try {
				cmd = bufferedreader.readLine();
				// Payload CMD
				if (cmd != null && cmd.startsWith("MSG")) {
					String tokens[] = cmd.split(" ");
					int len = Integer.valueOf(tokens[3]);
					char[] cbuf = new char[len];
					bufferedreader.read(cbuf, 0, len);
					cmd += EL + new String(cbuf);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (_commandListener != null && cmd != null)
			_commandListener.reveivedCommand(cmd + EL);
		return cmd;
	}

	@Override
	public void run() {
		listen();
	}

	public void listen() {
		boolean cont = true;
		int j = 0;
		while (cont) {
			String cmd = nextCommand();
			if (cmd != null && cmd != "") {
				StringTokenizer token = new StringTokenizer(cmd);
				String tokens[] = new String[token.countTokens()];
				int i = 0;
				while (token.hasMoreTokens()) {
					tokens[i] = token.nextToken();
					i++;
				}
				execute(tokens[0], tokens);
				j++;
			}
		}
	}

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
