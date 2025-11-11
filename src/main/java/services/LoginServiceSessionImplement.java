package services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService {
    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        //hago una condicion preguntando si la cookie es distinta  de null
        //si es verdadero obtenga la cookie caso contrario creo un nuevo objeto
        //de la cookie
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                //convertimos la cookie a tipo string
                .map(Cookie::getValue)
                .findAny();
    }
}
