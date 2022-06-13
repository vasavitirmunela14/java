package com.cba.book.exception;

import java.util.Date;
import java.util.List;

public class ErrorResponse {
        private int statusCode;
        private List<String> message;
        private Date timeStamp;

        public ErrorResponse(int statusCode,List<String> message,Date timeStamp)
        {
            this.statusCode=statusCode;
            this.message = message;
            this.timeStamp = timeStamp;
        }

        public ErrorResponse() {
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public List<String> getMessage() {
            return message;
        }

        public void setMessage(List<String> message) {
            this.message = message;
        }
        public Date getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
        }
    }


