package com.toomanythoughts.tmt.web.logic.security;

import java.time.Duration;

public class SecurityConstants {

	public static final String Secret = "TooManyThoughts_TooManySecrets";
	public static final long Expiration_Time = Duration.ofDays(23).toMillis();
	public static final String Token_Prefix = "Bearer ";
	public static final String Header_String = "Authorization";
}
