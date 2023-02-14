package source.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import source.domain.UserRole;
import source.shared.FilterService;

@WebFilter("/product")
public class ProductFilter implements Filter {
	private FilterService filterService = FilterService.getFilterService();

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		chain.doFilter(request, response);
		filterService.doFilterValidation(request, response, chain, Arrays.asList(UserRole.USER));
	}
}
