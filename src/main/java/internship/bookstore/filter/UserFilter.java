package internship.bookstore.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internship.bookstore.entities.User;

@WebFilter(urlPatterns = { "/*" })
public class UserFilter implements Filter {

	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList("", "/login", "/logout", "/register", "/css/login.css", "/errorPage")));

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String errorURI = request.getContextPath() + "/errorPage";
		String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
		boolean allowedPath = ALLOWED_PATHS.contains(path);
		boolean loggedIn = session != null && session.getAttribute("user") != null;
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		if ((loggedIn || allowedPath)) {
			if (user == null) {
				chain.doFilter(request, response);
			} else if (user.getRole().getRolename().equals("USER")
					&& (path.contains("add") || path.contains("delete"))) {
				session.invalidate();
				response.sendRedirect(errorURI);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			response.sendRedirect(errorURI);
		}
	}

}
