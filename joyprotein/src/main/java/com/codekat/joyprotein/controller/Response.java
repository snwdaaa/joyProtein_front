package com.codekat.joyprotein.controller;
import java.util.List;

import lombok.Data;

@Data
public class Response {
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private Choice[] choices;

    @Data
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }

    @Data
    public static class Choice {
        private Message message;
        private String finish_reason;
        private int index;

        @Data
        public static class Message {
            private String role;
            private String content;
        }
    }
}
