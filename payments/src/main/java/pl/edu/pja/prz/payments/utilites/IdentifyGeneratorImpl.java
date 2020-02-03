package pl.edu.pja.prz.payments.utilites;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class IdentifyGeneratorImpl implements IdentifyGenerator {

	public IdentifyGeneratorImpl() {
	}

	@Override
	public String generateId() {
		var id = UUID.randomUUID();
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(id.getMostSignificantBits());
		bb.putLong(id.getLeastSignificantBits());
		return Base64.encodeBase64URLSafeString(bb.array());
	}
}

