package com.tobeto.ChatterBoxBackend.core.utilities.messages;

public interface ProjectMessageService {

    String getMessage(String keyword);
    String getMessageWithParams(String keyword, Object... params);

}
