package com.srivn.works.smlibrary.util;

import jakarta.annotation.Nullable;

public class AppConstants {

	public enum ErrorMessage {
		ERROR_0000("UNKNOWN", "Unknown Error, Please Try Again!"), ERROR_0001("DNF", "%s not found"),
		ERROR_0002("DUP", "%s already present.");

		private final String code;
		private final String msg;

		ErrorMessage(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public String getMsgWithParam(@Nullable String... params) {
			return getMsg().format(msg, params);
		}

	}

	public enum AppMessages {
		MSG_0001("ADDED", "Data Saved succesfully");

		private final String code;
		private final String msg;

		AppMessages(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

	}
}
