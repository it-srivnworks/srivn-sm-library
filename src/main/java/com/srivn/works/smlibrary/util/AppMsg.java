package com.srivn.works.smlibrary.util;

import jakarta.annotation.Nullable;

public class AppMsg {

	public enum Err {
		ERR_000("UNKNOWN", "Unknown Error, Please Try Again!"), 
		ERR_001("DNF", "%s not found"),
		ERR_002("DUP", "%s already present.");

		private final String code;
		private final String msg;

		Err(String code, String msg) {
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

	public enum Msg {
		MSG_000("OK", "Howdy!"),
		MSG_001("ADDED", "Data Saved succesfully");

		private final String code;
		private final String msg;

		Msg(String code, String msg) {
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
