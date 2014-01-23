import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;

import com.mulesoft.mule.tracking.event.EventNotification;
import com.mulesoft.mule.tracking.event.EventNotificationListener;


public class MyMuleContextNotificationListener implements
		EventNotificationListener<EventNotification>, MuleContextAware {
	
	MuleContext context;
    protected static final Log LOGGER = LogFactory.getLog("MEEE");

	@Override
	public void onNotification(EventNotification notification) {
		LOGGER.error("Notification: " + notification.toString());
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.context = context;
		
	}

}
