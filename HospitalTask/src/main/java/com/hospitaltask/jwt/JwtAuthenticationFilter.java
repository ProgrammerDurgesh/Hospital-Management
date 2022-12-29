/*
 * package com.hospitaltask.jwt;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.apache.tomcat.util.bcel.classfile.Constant; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource;
 * 
 * import io.jsonwebtoken.ExpiredJwtException; import
 * io.jsonwebtoken.SignatureException;
 * 
 * public class JwtAuthenticationFilter { // private static final Logger logger
 * = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * @Autowired private TokenProvider tokenProvider;
 * 
 * protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws IOException,
 * ServletException { String header = request.getHeader(HEADER_STRING); String
 * email = null; String authToken = null; if (header != null &&
 * header.startsWith(TOKEN_PREFIX)) { authToken = header.replace(TOKEN_PREFIX,
 * ""); try { email = tokenProvider.getUsernameFromToken(authToken); } catch
 * (IllegalArgumentException e) { //
 * logger.error(Constant.GetEmailFromTokenException, authToken, e.getMessage());
 * } catch (ExpiredJwtException e) {
 * //logger.warn(Constant.TokenExpiredException, authToken, e.getMessage()); }
 * catch (SignatureException e) { // logger.error(Constant.SignatureException,
 * authToken, e.getMessage()); } } else { //
 * logger.warn("couldn't find bearer string, will ignore the header"); } if
 * (email != null && SecurityContextHolder.getContext().getAuthentication() ==
 * null) { UserDetails userDetails = (UserDetails)
 * userDetailsService.loadUserByUsername(email);
 * 
 * if (tokenProvider.validateToken(authToken, userDetails)) {
 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
 * UsernamePasswordAuthenticationToken(userDetails, authToken,
 * userDetails.getAuthorities());
 * usernamePasswordAuthenticationToken.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request)); //
 * logger.info("authenticated user " + email + ", setting security context");
 * SecurityContextHolder.getContext().setAuthentication(
 * usernamePasswordAuthenticationToken); } } filterChain.doFilter(request,
 * response); } }
 */