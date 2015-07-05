package com.ktds.high.member.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionStore implements Serializable{

	private static final long serialVersionUID = -1868322926509648250L;

	Map<String, HttpSession> sessions ;
	
	private static SessionStore sessionStore;
	
	private SessionStore(){
		sessions = new HashMap<String, HttpSession>();
	}

	public static synchronized SessionStore getInstance() {
		if(SessionStore.sessionStore == null){
			SessionStore.sessionStore = new SessionStore();
		}
		return sessionStore;
	}
	
	public synchronized void putSession(String memberId, HttpSession session) {
		
		this.sessions.put(memberId, session);
	}
	
	public synchronized HttpSession getSession(String memberId){
		return this.sessions.get(memberId);
	}
	
	public synchronized void removeSession(String memberId){
		this.sessions.remove(memberId);
	}
	
	public synchronized boolean isExists(String memberId){
		return this.sessions.containsKey(memberId);
	}
	
	public synchronized int getSize(){
		return this.sessions.size();
	}
	
}
