package cc.stdrc.veripressx.utils;

import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ViewUtils {
    public static void setErrors(ModelMap map, Errors errors) {
        if (map.containsAttribute("errors")) {
            map.remove("errors");
        }
        map.addAttribute(
                "errors",
                errors.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toArray(String[]::new)
        );
    }
}
