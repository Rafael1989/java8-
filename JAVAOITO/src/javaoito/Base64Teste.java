/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaoito;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 *
 * @author User
 */
public class Base64Teste {
    
    public static void main(String args[]) {

      try {
		
         // Encode using basic encoder
         String base64encodedString = Base64.getEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
         System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
         //Base64 Encoded String (Basic) :VHV0b3JpYWxzUG9pbnQ/amF2YTg=
		
         // Decode
         byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
		
         System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
         //Original String: TutorialsPoint?java8
         base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
         System.out.println("Base64 Encoded String (URL) :" + base64encodedString);
         //Base64 Encoded String (URL) :VHV0b3JpYWxzUG9pbnQ_amF2YTg=
		
         StringBuilder stringBuilder = new StringBuilder();
		
         for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
         }
		
         byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
         String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
         System.out.println("Base64 Encoded String (MIME) :" + mimeEncodedString);
         //Base64 Encoded String (MIME) :YmU3NWY2ODktNGM5YS00ODlmLWI2MTUtZTVkOTk2YzQ1Njk1Y2EwZTg2OTEtMmRiZC00YTQ1LWJlNTctMTI1MWUwMTk0ZWQyNDE0NDAwYjgtYTYxOS00NDY5LTllYTctNjc1YzE3YWJhZTk1MTQ2MDQz
         //NDItOTAyOC00ZWI0LThlOTYtZWU5YzcwNWQyYzVhMTQxMWRjYTMtY2MwNi00MzU0LTg0MTgtNGQ1
         //MDkwYjdiMzg2ZTY0OWU5MmUtZmNkYS00YWEwLTg0MjQtYThiOTQxNDQ2YzhhNTVhYWExZjItNjU2
         //Mi00YmM4LTk2ZGYtMDE4YmY5ZDZhMjkwMzM3MWUzNDMtMmQ3MS00MDczLWI0Y2UtMTQxODE0MGU5
         //YjdmYTVlODUxYzItN2NmOS00N2UyLWIyODQtMThlMWVkYTY4M2Q1YjE3YTMyYmItZjllMS00MTFk
         //LWJiM2UtM2JhYzUxYzI5OWI4

      } catch(UnsupportedEncodingException e) {
         System.out.println("Error :" + e.getMessage());
      }
   }
    
}
