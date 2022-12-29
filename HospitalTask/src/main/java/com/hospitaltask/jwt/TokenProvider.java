/*
 * package com.hospitaltask.jwt;
 * 
 * import java.util.Date; import java.util.function.Function;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component;
 * 
 * import com.hospitaltask.repository.DoctorRepo;
 * 
 * import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts;
 * 
 * @Component public class TokenProvider {
 * 
 * @Autowired private DoctorRepo doctorRepo;
 * 
 * public String getUsernameFromToken(String token) { return
 * getClaimFromToken(token, Claims::getSubject); }
 * 
 * public Date getExpirationDateFromToken(String token) { return
 * getClaimFromToken(token, Claims::getExpiration); }
 * 
 * public String getIdFromToken(String token) { return getClaimFromToken(token,
 * Claims::getId); }
 * 
 * public <T> T getClaimFromToken(String token, Function<Claims, T>
 * claimsResolver) { final Claims claims = getAllClaimsFromToken(token); return
 * claimsResolver.apply(claims); }
 * 
 * private Claims getAllClaimsFromToken(String token) { return Jwts.parser()
 * .setSigningKey(Constant.SIGNING_KEY) .parseClaimsJws(token) .getBody(); }
 * 
 * private Boolean isTokenExpired(String token) { final Date expiration =
 * getExpirationDateFromToken(token); return expiration.before(new Date()); }
 * 
 * public String generateToken(Authentication authentication) throws
 * IOException, ServletException { final User user = (User)
 * authentication.getPrincipal(); final String username = user.getUsername();
 * final com.brainerhub.loan.entity.User users =
 * userRepository.findUserByEmail(username); final String authorities =
 * authentication.getAuthorities().stream() .map(GrantedAuthority::getAuthority)
 * .collect(Collectors.joining(","));
 * 
 * return Jwts.builder() .setSubject(authentication.getName())
 * .setId(users.getId().toString()) .claim(Constant.AUTHORITIES_KEY,
 * authorities) .signWith(SignatureAlgorithm.HS256, Constant.SIGNING_KEY)
 * .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration(new
 * Date(System.currentTimeMillis() + Constant.ACCESS_TOKEN_VALIDITY_SECONDS *
 * 1000)) .compact(); }
 * 
 * public Boolean validateToken(String token, UserDetails userDetails) { final
 * String username = getUsernameFromToken(token); return (
 * username.equals(userDetails.getUsername()) && !isTokenExpired(token)); }
 * 
 * UsernamePasswordAuthenticationToken getAuthentication(final String token,
 * final Authentication existingAuth, final UserDetails userDetails) {
 * 
 * final JwtParser jwtParser =
 * Jwts.parser().setSigningKey(Constant.SIGNING_KEY);
 * 
 * final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
 * 
 * final Claims claims = claimsJws.getBody();
 * 
 * final Collection<? extends GrantedAuthority> authorities =
 * Arrays.stream(claims.get(Constant.AUTHORITIES_KEY).toString().split(","))
 * .map(SimpleGrantedAuthority::new) .collect(Collectors.toList());
 * 
 * return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
 * }
 * 
 * }
 */