package lindacare.jcunha.techtest.messageprocessor;

import static java.util.Collections.emptySet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint("/processor")
public class MessagesProcessor {
  
	private final static Logger logger = Logger.getLogger(MessagesProcessor.class);
	private static final Queue<Session> QUEUE = new ConcurrentLinkedQueue<Session>();
	public static final AtomicReference<Set<Session>> sessions = new AtomicReference<>(emptySet());
	
	public void onMessage(Session session, String message) {}
	
	@OnOpen
	public void open(Session session) {
		QUEUE.add(session);
	}
	@OnError
	public void error(Session session, Throwable t) {
	    
	    QUEUE.remove(session);
	}

	@OnClose
	public void closedConnection(Session session) {
	    QUEUE.remove(session);
	}
	
	  @OnOpen
	  public void onOpen(Session session, EndpointConfig config) {
		  logger.info("socket open ");
	    sessions.set(session.getOpenSessions());
	  }

	  @OnMessage
	  public void onMessage(String message, Session session) {
		  logger.info("A new message has arrived: " + message);
	    session.getAsyncRemote().sendText("A new message has arrived: " + message);
	  }
	  
	  @OnClose
	  public void onClose(Session session) {
	    sessions.set(session.getOpenSessions());
	  }

	  public static void sendToAll(String message) throws IOException {
		    ArrayList<Session> closedSessions = new ArrayList<Session>();
		    for (Session session : QUEUE) {
		        if (!session.isOpen()) {
		            closedSessions.add(session);
		        } else {
		            session.getBasicRemote().sendText(message);
		        }
		    }
		    QUEUE.removeAll(closedSessions);
		}


}
