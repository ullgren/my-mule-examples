package melexamples;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class DoNothing implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		System.out.println(eventContext);
		return eventContext;
	}

}
