package org.mdubois.freeveggie.framework.play;

import java.util.Calendar;
import java.util.Date;

import play.Play;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Result;

public class AuthenticatedAction extends Action<Authenticated> {

	private static final Long TIMEOUT_IN_SECOND = new Long(Play.application().configuration()
			.getString("application.session.maxAge")) * 1000;

	@Override
	public Result call(Context ctx) throws Throwable {
		Session session = ctx.session();
		// Check if the user is identify
		String userId = session.get("connected");
		if (userId != null) {
			Date lastAction = new Date();
			if (session.get("lastActionTime") != null) {
				lastAction = new Date();
				lastAction.setTime(Long.parseLong(session.get("lastActionTime")));
				if (lastAction.getTime() < Calendar.getInstance().getTimeInMillis() - TIMEOUT_IN_SECOND) {
					session.clear();
					return redirect("/login");
				}
			} else {
				// This should not be able
				session.clear();
				return redirect("/login");
			}

			return delegate.call(ctx);
		} else {
			return redirect("/login");
		}

	}

}
