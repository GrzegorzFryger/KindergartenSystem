package pl.edu.pja.prz.receivables.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public enum CharsetEncoding {
    UTF8(StandardCharsets.UTF_8),
    CP1250(Charset.forName("Cp1250"));

    private Charset charset;

    CharsetEncoding(Charset charset) {
        this.charset = charset;
    }

    public Charset getCharset() {
        return charset;
    }

    public static Charset charsetFromString(String charset) {
        for (CharsetEncoding encoding : CharsetEncoding.values()) {
            if (encoding.toString().equalsIgnoreCase(charset)) {
                return encoding.getCharset();
            }
        }

        return CP1250.getCharset(); //Currently default encoding
    }
}
