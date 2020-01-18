package pl.edu.pja.prz.payments.utilites;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class IdentifyGenerator {

	public IdentifyGenerator() {
	}

	public String generateId() {
		Base64 base64 = new Base64();
		var id = UUID.randomUUID();

		System.out.println(id.toString());
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(id.getMostSignificantBits());
		bb.putLong(id.getLeastSignificantBits());
		return Base64.encodeBase64URLSafeString(bb.array());
	}
}

