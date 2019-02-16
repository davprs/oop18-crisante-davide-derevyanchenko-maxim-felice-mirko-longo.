package view;

import java.util.ResourceBundle;

import controller.utilities.BundleUtils;

/**
 * View of AccountError.
 *
 */
public class AccountErrorView extends ErrorView {

    private static final String BUNDLE = "errors.accountError.AccountErrorBundle";
    private final String language;

    /**
     * Construct the View, according to the language.
     * @param language the desired language
     */
    public AccountErrorView(final String language) {
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

