package view.register;

import java.util.ResourceBundle;

import utilities.BundleUtils;
import view.ErrorView;

/**
 * View of RegisterPasswordError.
 *
 */
public class RegisterPasswordErrorView extends ErrorView {

    private static final String DEFAULT = "en";
    private static final String BUNDLE = "errors.register.passwordError.PasswordErrorBundle";
    private final String language;

    /**
     * Construct the default View.
     */
    public RegisterPasswordErrorView() {
        this.language = DEFAULT;
    }

    /**
     * Construct the View, according to the language.
     * @param language the desired language
     */
    public RegisterPasswordErrorView(final String language) {
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
