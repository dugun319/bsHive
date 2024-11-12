package com.postGre.bsHive.Adto;

import lombok.Data;

// ajax 성공여부 dto

@Data
public class ResponseMessage {
	private boolean success;	//요청 성공 여부
	private String	message;	//결과 메세지
	
	public ResponseMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
	
	@Override
    public String toString() {
        return "ResponseMessage{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}	
