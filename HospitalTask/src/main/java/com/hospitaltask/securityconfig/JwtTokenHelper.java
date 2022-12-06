package com.hospitaltask.securityconfig;


import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

    public  static final long JWT_TOKEN_VALIDITY=5*60*60;

    private String secret="tokenized";

    //retrieve Username from token
    public String getUsernameFromToken(String token)
        {
            return getClaimFromToken( token, Claims::getSubject );
        }
    public Date getExpirationDateFromToken( String token)
        {
            return getClaimFromToken( token, Claims::getExpiration );
        }

        //for retrieving any information  from token we will need the Secret key
    public <T> T  getClaimFromToken ( String token , Function<Claims,T> claimResolver)
        {
            final Claims claims=getClaimFromToken ( String token );
            return (T) Jwts.parser ().setSigningKey ( secret ).parseClaimsJws ( token ).getBody ();
        }


        //Check if Token is Expired
        boolean isTokenExpired(String token)
            {
                final Date date=getExpirationDateFromToken ( token );
                return date.before ( date );
            }

            //Generate Token For User
            JwtBuilder generateToken( UserDetails userDetails )
                {
                    Map<String,Object> claims=new HashMap <> (  );
                    return doGenerateToken(claims,userDetails.getUsername ());
                }


    JwtBuilder doGenerateToken ( Map< String, Object> claims , String username )
        {
            return Jwts.builder ().setClaims ( claims ).setSubject ( username ).setIssuedAt ( new Date (System.currentTimeMillis () ))
                    .setExpiration ( new Date ( System.currentTimeMillis ()  +JWT_TOKEN_VALIDITY*100)).signWith ( SignatureAlgorithm.HS512,secret );
        }

        boolean validateToken(String token ,UserDetails userDetails)
            {
                final  String username=getUsernameFromToken ( token );
                return (username.equals ( userDetails.getUsername () )&& ! isTokenExpired ( token ));

            }

}
