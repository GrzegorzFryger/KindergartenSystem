package pl.edu.pja.prz.account.model.value;

public class IdentityObject<T> {

	private final T id;
	private final String additionalInfo;

	public IdentityObject(T id, String additionalInfo) {
		this.id = id;
		this.additionalInfo = additionalInfo;
	}

	public T getId() {
		return id;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}
}
