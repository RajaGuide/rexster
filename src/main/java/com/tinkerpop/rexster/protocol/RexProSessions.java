package com.tinkerpop.rexster.protocol;

import com.tinkerpop.rexster.RexsterApplication;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RexProSessions {
    private static final Logger logger = Logger.getLogger(AbstractRexProSession.class);

    protected static ConcurrentHashMap<UUID, AbstractRexProSession> sessions = new ConcurrentHashMap<UUID, AbstractRexProSession>();

    public static AbstractRexProSession getSession(UUID sessionKey) {
        return sessions.get(sessionKey);
    }

    public static void destroySession(UUID sessionKey) {
        sessions.remove(sessionKey);
        logger.info("RexPro Session destroyed: " + sessionKey.toString());
    }

    public static void destroyAllSessions() {
        Iterator<UUID> keys = sessions.keySet().iterator();
        while (keys.hasNext()) {
            UUID keyToRemove = keys.next();
            destroySession(keyToRemove);
            logger.info("RexPro Session destroyed: " + keyToRemove.toString());
        }
    }

    public static boolean hasSessionKey(UUID sessionKey) {
        return sessions.containsKey(sessionKey);
    }

    public static Collection<UUID> getSessionKeys() {
        return sessions.keySet();
    }

    public static void ensureSessionExists(UUID sessionKey, RexsterApplication rexsterApplication, byte sessionChannel) {
        if (!sessions.containsKey(sessionKey)) {

            AbstractRexProSession session = RexProSessionFactory.createInstance(sessionKey, rexsterApplication, sessionChannel);
            if (session == null) {
                logger.warn("A RexPro Session could not be created because the requested channel is not valid.");
                throw new RuntimeException("Requested channel is not valid.");
            }

            sessions.put(sessionKey, session);
            logger.info("RexPro Session created: " + sessionKey.toString());
        }
    }

}
