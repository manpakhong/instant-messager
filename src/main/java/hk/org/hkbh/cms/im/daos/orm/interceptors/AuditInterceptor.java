package hk.org.hkbh.cms.im.daos.orm.interceptors;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor {
	private final Logger logger = Logger.getLogger(this.getClass());
	private String getClassName(){
		return this.getClass().getName();
	}	
    private int updates;
    private int creates;
    private int loads;

    public void onDelete(Object entity,
                         Serializable id,
                         Object[] state,
                         String[] propertyNames,
                         Type[] types) {
        // do nothing
    	return;
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {

//        if ( entity instanceof Auditable ) {
//            updates++;
//            for ( int i=0; i < propertyNames.length; i++ ) {
//                if ( "lastUpdateTimestamp".equals( propertyNames[i] ) ) {
//                    currentState[i] = new Date();
//                    return true;
//                }
//            }
//        }
//        return false;
    	
        logger.debug( "Entity " + entity.getClass().getSimpleName() + "#" + id + "changed from " + Arrays.toString( previousState ) +  "to " + Arrays.toString( currentState )
            );
            return super.onFlushDirty( entity, id, currentState,
                previousState, propertyNames, types);
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
//        if ( entity instanceof Auditable ) {
//            loads++;
//        }
    	logger.info(entity);
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {

//        if ( entity instanceof Auditable ) {
//            creates++;
//            for ( int i=0; i<propertyNames.length; i++ ) {
//                if ( "createTimestamp".equals( propertyNames[i] ) ) {
//                    state[i] = new Date();
//                    return true;
//                }
//            }
//        }
        return false;
    }

    public void afterTransactionCompletion(Transaction tx) {
        if ( tx.getStatus() == TransactionStatus.COMMITTED ) {
            logger.info("Creations: " + creates + ", Updates: " + updates + "Loads: " + loads);
        }
        updates=0;
        creates=0;
        loads=0;
    }
}
