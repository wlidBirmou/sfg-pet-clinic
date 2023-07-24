package guru.springframework.sfgpetclinic.utilities;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class WebUtilities {


    public final static String WEB_BASE_URL= ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
}
