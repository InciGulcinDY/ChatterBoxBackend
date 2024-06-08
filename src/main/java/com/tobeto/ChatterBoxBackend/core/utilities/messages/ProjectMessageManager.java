package com.tobeto.ChatterBoxBackend.core.utilities.messages;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class ProjectMessageManager implements ProjectMessageService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String keyword) {
        if (keyword == null) {
            return "Geçersiz anahtar kelime";
        }
        return messageSource.getMessage(keyword, null, new Locale("en", "US"));
    }

    @Override
    public String getMessageWithParams(String keyword, Object... params) {
        return messageSource.getMessage(keyword,params, LocaleContextHolder.getLocale());
    }
}