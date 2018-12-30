package cc.stdrc.veripressx.utils;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ViewUtils {
    public static void setErrors(Model model, Errors errors) {
        model.addAttribute(
                "errors",
                errors.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toArray(String[]::new)
        );
    }

    public static void setErrors(RedirectAttributes redirectAttrs, Errors errors) {
        redirectAttrs.addFlashAttribute(
                "errors",
                errors.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toArray(String[]::new)
        );
    }
}
