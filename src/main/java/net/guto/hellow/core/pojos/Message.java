package net.guto.hellow.core.pojos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Message {

	private static final String EL = "\r\n";
	private static final short TYPE_CONTROL = 1;
	private static final short TYPE_MSG = 2;

	private int trid;
	private Map<String, String> header;
	private String body;
	private short messageType;

	private Map<String, String> extractMsgHeader(String message) {
		Map<String, String> headerMap = new LinkedHashMap<String, String>();
		int cutter = message.indexOf(EL + EL);
		String header = message.substring(0, cutter);
		String parameters_values[] = header.split(EL);
		for (String parameter_value : parameters_values) {
			if (parameter_value.startsWith("MSG")) {
				// TODO: decode http response 200;
			} else {
				cutter = parameter_value.indexOf(":");
				String parameter = parameter_value.substring(0, cutter).trim();
				String value = parameter_value.substring(cutter + 1,
						parameter_value.length()).trim();
				headerMap.put(parameter, value);
			}
		}
		return headerMap;
	}

	private String extractBody(String message) {
		int cutter = message.indexOf(EL + EL) + 4;
		return message.substring(cutter, message.length());
	}

	public Message(String command) {
		header = extractMsgHeader(command);
		body = extractBody(command);
	}

	public Message(String message, int trid) {
		header = new LinkedHashMap<String, String>();
		this.trid = trid;
		messageType = TYPE_MSG;
		body = message;
		header = new LinkedHashMap<String, String>();
		header.put("MIME-Version", "1.0");
		header.put("Content-Type", "text/plain; charset=UTF-8");
		header.put("X-MMS-IM-Format", "FN=MS%20Sans%20Serif; EF=; CO=0; CS=0; PF=0");
	}
	
	public Message(int trid, String username) {
		this.trid = trid;
		messageType = TYPE_CONTROL;
		body = EL;
		header = new LinkedHashMap<String, String>();
		header.put("MIME-Version", "1.0");
		header.put("Content-Type", "text/x-msmsgscontrol");
		header.put("TypingUser", username);
	}

	public String formatHeader() {
		StringBuilder headerStr = new StringBuilder();
		for (Entry<String, String> entry : header.entrySet()) {
			headerStr.append(entry.getKey());
			headerStr.append(": ");
			headerStr.append(entry.getValue());
			headerStr.append(EL);
		}
		return headerStr.toString();
	}
	
	public String send() {
		StringBuilder command = new StringBuilder(); 
		command.append(formatHeader());
		command.append(EL);
		command.append(body);
		String mode = (messageType == TYPE_CONTROL)?" U ":" N ";
		String cmd = "MSG "+ trid + mode + command.length() + EL + command.toString();
		return cmd;
	}
	
	public Map<String, String> getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}

}
