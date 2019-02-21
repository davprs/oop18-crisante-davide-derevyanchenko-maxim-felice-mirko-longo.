package view;

import java.util.ResourceBundle;

import utilities.BundleUtils;

/**
 * View of PasswordError.
 *
 */
public class PasswordErrorView extends ErrorView {

    private static final String BUNDLE = "errors.passwordError.PasswordErrorBundle";
    private final String language;

    /**
     * Construct the View, according to the language.
     * @param language the desired language
     */
    public PasswordErrorView(final String language) {
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setLanguage() {
        BundleUtils.setLocale(language);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceBundle getBundle() {
        return ResourceBundle.getBundle(BUNDLE);
    }
}
