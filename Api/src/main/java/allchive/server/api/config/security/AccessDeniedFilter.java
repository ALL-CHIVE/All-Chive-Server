package allchive.server.api.config.security;

import static allchive.server.core.consts.AllchiveConst.SwaggerPatterns;

import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.ErrorResponse;
import allchive.server.core.error.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class AccessDeniedFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return PatternMatchUtils.simpleMatch(SwaggerPatterns, servletPath);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BaseErrorException e) {
            handleException(response, getErrorResponse(e));
        } catch (AccessDeniedException e) {
            ErrorResponse access_denied =
                    ErrorResponse.from(GlobalErrorCode.INVALID_ACCESS_TOKEN_ERROR.getErrorReason());
            handleException(response, access_denied);
        }
    }

    private ErrorResponse getErrorResponse(BaseErrorException e) {
        return ErrorResponse.from(e.getErrorReason());
    }

    private void handleException(HttpServletResponse response, ErrorResponse errorResponse)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
