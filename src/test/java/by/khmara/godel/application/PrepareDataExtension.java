package by.khmara.godel.application;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class PrepareDataExtension implements BeforeAllCallback {
	private static boolean isStarted = false;

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		if (!isStarted) {
			isStarted = true;


		}
	}
}
